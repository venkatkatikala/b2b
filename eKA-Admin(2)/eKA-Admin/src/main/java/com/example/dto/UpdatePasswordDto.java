package com.example.dto;

import lombok.Data;

@Data
public class UpdatePasswordDto {
private String email;
private String otp;
private String newpassword;
}
