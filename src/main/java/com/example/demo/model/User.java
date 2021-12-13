package com.example.demo.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class User {
	
	@NotNull
	@Min(1)
	@Max(100)
	private Integer id;
	
	@NotBlank
	@Length(min = 1, max = 20)
	private String name;
	
	@NotNull
	@Min(1)
	@Max(1000)
	private Integer price;

}
