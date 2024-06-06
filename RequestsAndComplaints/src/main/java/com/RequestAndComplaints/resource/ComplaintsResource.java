package com.RequestAndComplaints.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.RequestAndComplaints.dao.UserRepository;
import com.RequestAndComplaints.dto.CommonApiResponse;
import com.RequestAndComplaints.entity.Complaints;
import com.RequestAndComplaints.entity.User;
import com.RequestAndComplaints.service.ComplaintsService;

@Service
public class ComplaintsResource {

	@Autowired
	ComplaintsService service;
	@Autowired
	UserRepository dao;
	
	public ResponseEntity<CommonApiResponse>addcomplaints(Complaints com){
		CommonApiResponse response = new CommonApiResponse();
		
		User user = dao.findByEmpnumber(com.getEmpnumber());
		if(user==null) {
			response.setMessage("your not authorized person");
			response.setStatus(false);
			return new ResponseEntity<CommonApiResponse>(response,HttpStatus.NOT_FOUND);
		}
		Complaints addcomplaints = service.addcomplaints(com);
		if(addcomplaints==null) {
			response.setMessage("failed to send complaint");
			response.setStatus(false);
			return new ResponseEntity<CommonApiResponse>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.setMessage("successfully send complaint");
		response.setStatus(true);
		return new ResponseEntity<CommonApiResponse>(response,HttpStatus.OK);
	}
	
	public ResponseEntity<CommonApiResponse>findcomplaintsByempnumber(long empnumber){
		CommonApiResponse response = new CommonApiResponse();
		
		List<Complaints> findbyempnumber = service.findbyempnumber(empnumber);
		
		if(findbyempnumber.isEmpty()) {
			response.setMessage("no complaints found");
			response.setStatus(false);
			return new ResponseEntity<CommonApiResponse>(response,HttpStatus.NOT_FOUND);
		}
		response.setListcomplaints(findbyempnumber);
		response.setMessage("data found");
		response.setStatus(true);
		return new ResponseEntity<CommonApiResponse>(response,HttpStatus.OK);
	}
	
	public ResponseEntity<CommonApiResponse>findallcomplaints(){
		
		CommonApiResponse response = new CommonApiResponse();
		
		List<Complaints> findallcomplaints = service.findallcomplaints();
		if(findallcomplaints.isEmpty()) {
			response.setMessage("no complaints found");
			response.setStatus(false);
			return new ResponseEntity<CommonApiResponse>(response,HttpStatus.NOT_FOUND);
		}
		
		response.setListcomplaints(findallcomplaints);
		response.setMessage("data found");
		response.setStatus(true);
		return new ResponseEntity<CommonApiResponse>(response,HttpStatus.OK);
	}
}
