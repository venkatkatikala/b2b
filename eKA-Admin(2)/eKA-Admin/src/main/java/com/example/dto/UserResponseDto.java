package com.example.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.entity.LeaveBalance;
import com.example.entity.LeaveRequest;
import com.example.entity.User;

import lombok.Data;

@Data
public class UserResponseDto extends CommonApiResponse {

	private List<User>user=new ArrayList<>();
	
	private List<LeaveBalance>leaveBalance=new ArrayList<>();
	private List<LeaveRequest>leaverequest=new ArrayList<>();
}
