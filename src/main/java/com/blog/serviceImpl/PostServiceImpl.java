package com.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.entity.Post;
import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.Post2Dto;
import com.blog.payload.Post3Dto;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.repository.CategoryRepository;
import com.blog.repository.PostRepository;
import com.blog.repository.UserRepository;
import com.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CategoryRepository catRepo;
	
	@Override
	public PostDto createPostByUser(PostDto dto, int userId, int categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		Category category = this.catRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		Post post = this.mapper.map(dto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		
		this.postRepo.save(post);
		return this.mapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(int pageNumber , int pageSize , String sortBy , String sortDir) {
		// paggination
//		int pageSize = 5;
//		int pageNumber = 2;
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort = Sort.by(sortBy).ascending();
		} else {
			sort = Sort.by(sortBy).descending();
		}
		Pageable pg = PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> page = this.postRepo.findAll(pg);
		List<Post> list = page.getContent();
		
		
		List<PostDto> list2 = list.stream().map(p -> this.mapper.map(p, PostDto.class)).collect(Collectors.toList());
		
		PostResponse res = new PostResponse();
		res.setContents(list2);
		res.setPageNumber(page.getNumber());
		res.setPageSize(page.getSize());
		res.setLastPage(page.isLast());
		res.setTotalPages(page.getTotalPages());
		res.setTotalElements(page.getTotalElements());
		
		return res;
	}

	@Override
	public PostDto getPostById(Integer id) {
		Post post = this.postRepo
			.findById(id)
			.orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		
		return this.mapper.map(post, PostDto.class);
	}
	

	@Override
	public List<Post2Dto> getPostByUser(Integer userId) {
		User user = this.userRepo
		.findById(userId)
		.orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
		List<Post> list = this.postRepo.findByUser(user);
		List<Post2Dto> list2 = list.stream().map(p -> this.mapper.map(p, Post2Dto.class)).collect(Collectors.toList());
		return list2;
	}

	@Override
	public List<Post3Dto> getPostByCategory(Integer categoryId) {
		Category category = this.catRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "id", categoryId));
		List<Post> list = this.postRepo.findByCategory(category);
		List<Post3Dto> list2 = list.stream().map(p -> this.mapper.map(p, Post3Dto.class)).collect(Collectors.toList());
		return list2;
	}

	@Override
	public void deletePost(Integer id) {
		this.postRepo.findById(id)
					.orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));

	}

	@Override
	public PostDto updatePost(Integer id , PostDto dto) {
		Post post = this.postRepo.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
		
		post.setContent(dto.getContent());
		post.setTitle(dto.getTitle());
		
		Post save = this.postRepo.save(post);
		
		return this.mapper.map(save, PostDto.class);
	}




}
