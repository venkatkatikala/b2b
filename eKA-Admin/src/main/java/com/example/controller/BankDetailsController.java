package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CommonApiResponse;
import com.example.dto.UserResponseDto;
import com.example.entity.BankDetails;
import com.example.resource.BankDetailsResource;

@RestController
@RequestMapping("/bankdetails")
public class BankDetailsController {

	@Autowired
	BankDetailsResource resource;
	
	
	@PostMapping(value = "/addbankdetails")
	public ResponseEntity<CommonApiResponse>addbankdetails(@RequestBody BankDetails bank){
		return resource.addbankdetails(bank);
	}
	
	@GetMapping(value = "/findallbankdetails")
	public ResponseEntity<UserResponseDto>findallbankdetails(){
		return resource.findallemployessbankdetails();
	}
}
