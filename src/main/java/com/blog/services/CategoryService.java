package com.blog.services;

import java.util.List;

import com.blog.payload.CategoryDto;

public interface CategoryService {
	
	//add
	public CategoryDto createCategory(CategoryDto dto);
	// gett all
	public List<CategoryDto> getAllCategory();
	// get one
	public CategoryDto getCategoryById(Integer id);
	// delete 
	public void deleteCategory(Integer id);
	//update public update
	public CategoryDto updateCategory(Integer id);

}
