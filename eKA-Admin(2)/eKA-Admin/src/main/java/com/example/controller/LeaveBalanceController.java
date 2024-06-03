package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CommonApiResponse;
import com.example.dto.UserResponseDto;
import com.example.entity.LeaveBalance;
import com.example.resource.LeaveBalanceResource;

@RestController
@RequestMapping("/leaveBalance")
public class LeaveBalanceController {

	@Autowired
	LeaveBalanceResource resource;
	
	
	@PostMapping(value = "/addleaves")
	public ResponseEntity<CommonApiResponse>addleaves(@RequestBody List<LeaveBalance>leave){
		return resource.addleave(leave);
	}
	
	@GetMapping(value = "/availableLeaves")
	public ResponseEntity<UserResponseDto>checkavailabeleaves(@RequestParam String empnumber){
		return resource.checkavailabeleaves(empnumber);
	}
}
