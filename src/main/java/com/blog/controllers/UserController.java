package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.UserDto;
import com.blog.services.UserService;

@RestController
@RequestMapping("/v1/api/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto dto) {
		UserDto user = this.service.createUser(dto);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUserList(){
		List<UserDto> list = this.service.getAllUser();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable int id){
		
		UserDto dto = this.service.getUserById(id);
		return new ResponseEntity<>(dto ,HttpStatus.FOUND);
		
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id){
		this.service.deleteUserById(id);
		return ResponseEntity.ok("User Deleted Ok");
	}
	@PostMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable int id , @RequestBody UserDto dto){
		UserDto dto2 = this.service.updateUserById(id, dto);
		return ResponseEntity.ok(dto2);
		
	}

}







