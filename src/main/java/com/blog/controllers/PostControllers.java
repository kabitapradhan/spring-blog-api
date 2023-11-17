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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.Post2Dto;
import com.blog.payload.Post3Dto;
import com.blog.payload.PostDto;
import com.blog.payload.PostResponse;
import com.blog.services.PostService;
import com.fasterxml.jackson.annotation.JacksonInject.Value;


@RestController
@RequestMapping("/v1/api/post")
public class PostControllers {
	
	@Autowired
	private PostService service;
	
	@PostMapping("/user/{userId}/category/{catId}")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto dto ,
				@PathVariable Integer userId , @PathVariable Integer catId ) {
		PostDto post = this.service.createPostByUser(dto, userId, catId);
		return new ResponseEntity<>(post, HttpStatus.CREATED);
	}
	@GetMapping("/")
	public ResponseEntity<PostResponse> getAllPostList(
			@RequestParam(value="pageNumber" , defaultValue = "0" , required = false)Integer pageNumber,
			@RequestParam(value="pageSize" , defaultValue = "10" , required = false) Integer pageSize ,
			@RequestParam(value = "sortBy" , defaultValue = "title" , required = false) String sortBy,
			@RequestParam(value = "sortDir" ,defaultValue = "asc" ,required = false ) String sortDir
			){
		PostResponse obj = this.service.getAllPost(pageNumber , pageSize , sortBy , sortDir);
		return new ResponseEntity<>(obj,HttpStatus.OK);
	}
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Post2Dto>> getAllPostByUser(@PathVariable Integer userId){
		List<Post2Dto> list = this.service.getPostByUser(userId);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("/category/{catId}")
	public ResponseEntity<List<Post3Dto>> getAllPostByCategory(@PathVariable Integer catId){
		List<Post3Dto> list = this.service.getPostByCategory(catId);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto list = this.service.getPostById(postId);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostById(@PathVariable Integer id){
		this.service.deletePost(id);
		ApiResponse api = new ApiResponse("Delete post with this id : " + id +" success", true);
		return new ResponseEntity<>(api,HttpStatus.OK);
	}
	@PostMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto dto ,@PathVariable Integer id){
		PostDto post = this.service.updatePost(id , dto);
		return new ResponseEntity<PostDto>(post,HttpStatus.OK);
	}
}
