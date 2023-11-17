package com.blog.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.Category;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CategoryDto;
import com.blog.repository.CategoryRepository;
import com.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository repo;
	@Autowired ModelMapper mapper;

	@Override
	public CategoryDto createCategory(CategoryDto dto) {
		Category category = this.mapper.map(dto, Category.class);
		Category save = this.repo.save(category);
		return this.mapper.map(save, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> list = this.repo.findAll();
		List<CategoryDto> list2 = list.stream().map(ct -> this.mapper.map(ct, CategoryDto.class))
						.collect(Collectors.toList());
		return list2;
	}

	@Override
	public CategoryDto getCategoryById(Integer id) {
		Category category = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		
		return this.mapper.map(category, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer id) {
		Category category = this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		this.repo.delete(category);
	}

	@Override
	public CategoryDto updateCategory(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
