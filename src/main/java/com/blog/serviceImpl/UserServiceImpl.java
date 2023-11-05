package com.blog.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entity.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.UserDto;
import com.blog.repository.UserRepository;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public UserDto createUser(UserDto dto) {
		User user = this.mapper.map(dto, User.class);
		User user2 = this.userRepo.save(user);
		return this.mapper.map(user2, UserDto.class);
	}

	@Override
	public List<UserDto> getAllUser() {
		List<User> list = this.userRepo.findAll();
		List<UserDto> list2 = list.stream().map( mp -> this.mapper.map(mp, UserDto.class)).collect(Collectors.toList());
		
		return list2;
	}

	@Override
	public UserDto getUserById(int id) {
		User user = this.userRepo.findById(id)
		.orElseThrow(()-> new ResourceNotFoundException("User Database" , "user id" , id) );
		return this.mapper.map(user, UserDto.class);
	}

	@Override
	public UserDto updateUserById(int id, UserDto dto) {
		User user = this.userRepo
				.findById(id)
				.orElseThrow(()->  new ResourceNotFoundException("User Database" , "user id" , id) );
		user.setAbout(dto.getAbout());
		user.setName(dto.getName());
		user.setPassword(dto.getPassword());
		User save = this.userRepo.save(user);
		return this.mapper.map(save, UserDto.class);
	}

	@Override
	public void deleteUserById(int id) {
		User user = this.userRepo.findById(id)
				.orElseThrow(()->  new ResourceNotFoundException("User Database" , "user id" , id) );
		this.userRepo.delete(user);
		
	}

}
