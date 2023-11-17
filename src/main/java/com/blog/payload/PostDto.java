package com.blog.payload;

import java.util.Date;

import com.blog.entity.Category;
import com.blog.entity.User;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	
	private int postId;
	private String title;
	
	private String content;
	private String imageName;
	
	private Date addedDate;
	
	private UserDto user;
	private CategoryDto category;

}
