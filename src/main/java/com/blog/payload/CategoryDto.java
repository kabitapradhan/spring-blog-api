package com.blog.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor

public class CategoryDto {
	private int categoryId;
	private String categoryTitle;
	private String categoryDesc;

}
