package com.blog.services;

import java.util.List;

import com.blog.payload.Post2Dto;
import com.blog.payload.Post3Dto;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;

public interface PostService {
	
	//add
	public PostDto createPostByUser(PostDto dto , int userId , int categoryId);
	// gett all
	public PostResponse getAllPost(int pageNumber , int pageSize , String sortBy , String sortDir);
	// get one
	public PostDto getPostById(Integer id);
	public List<Post2Dto> getPostByUser(Integer userId);
	public List<Post3Dto> getPostByCategory(Integer categoryId);
	// delete 
	public void deletePost(Integer id);
	//update public update
	public PostDto updatePost(Integer id , PostDto dto);

}
