package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.CategoryDto;
import com.blog.services.CategoryService;


@RestController
@RequestMapping("/api/v2/category")
public class CategoryController {
	
	@Autowired
	private CategoryService service;
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createUser(@RequestBody CategoryDto dto) {
		CategoryDto user = this.service.createCategory(dto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllUserList(){
		List<CategoryDto> list = this.service.getAllCategory();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getUserById(@PathVariable int id){
		
		CategoryDto dto = this.service.getCategoryById(id);
		return new ResponseEntity<>(dto ,HttpStatus.FOUND);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id){
		this.service.deleteCategory(id);
		ApiResponse res = new ApiResponse("Delete Category data Succesfull", true);
		return new ResponseEntity<>(res , HttpStatus.OK);
	}

}
