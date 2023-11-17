package com.blog.payload;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Post3Dto {
	
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private UserDto user;

}
