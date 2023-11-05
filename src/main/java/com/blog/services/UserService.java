package com.blog.services;

import java.util.List;

import com.blog.entity.User;
import com.blog.payload.UserDto;

public interface UserService {
	
	// create user
	public UserDto createUser(UserDto dto);
	// get all user
	public List<UserDto> getAllUser();
	// get user by userID
	public UserDto getUserById(int id);
	//update user data(name , about , password);
	public UserDto updateUserById(int id , UserDto dto);
	// delete user
	public void deleteUserById(int id);

}
