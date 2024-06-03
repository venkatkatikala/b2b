package com.example.dto;

import org.springframework.beans.BeanUtils;

import com.example.entity.User;

import lombok.Data;

@Data
public class RequestUserDto {
	private String name;
	private String email;
	private String password;
	private String role;
	private String mobileno;
	 private String status;
	 private String position;
	
	 private String empNumber;
	
	public static User toUserEntity(RequestUserDto registerUserRequestDto) {
		User user = new User();
		BeanUtils.copyProperties(registerUserRequestDto, user);
		return user;
	}
}
