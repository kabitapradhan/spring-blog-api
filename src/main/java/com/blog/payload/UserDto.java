package com.blog.payload;

import java.util.ArrayList;
import java.util.List;

import com.blog.entity.Post;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	
	private int id;
	@NotBlank(message = "User name can not be blank !!")
	@NotNull(message = "User name can not be null !!")
	@Size(min=2 , message="User name must be minimum 2 character!!")
	private String name;
	@Email(message = "Email need to valid enter!!")
	private String email;
	@NotNull(message = "User about must be enter!!")
	private String about;
	@NotNull(message = "User password must be needed!!")
	@Size(min = 4 , message = "Password should be minimum 4 character!!")
	private String password;
	


}
