package com.example.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.attendance.dto.ResponseDto;
import com.example.feignclient.AnnouncementFeign;

@RestController
@RequestMapping("announceFeign")
public class AnnouncementController {
	
	@Autowired
	AnnouncementFeign feign;
	
	@GetMapping(value = "/getbirthdays")
	 public ResponseEntity<ResponseDto> findBirthdays() {
		return feign.findBirthdays();
	}
	
	@GetMapping(value = "/getnewjoiners")
	 public ResponseEntity<ResponseDto> findNewJoiners(){
		return feign.findNewJoiners();
	}
	
	@GetMapping(value = "/findallannouncements")
	public ResponseEntity<ResponseDto>fetchallannouncements(){
		return feign.fetchallannouncements();
	}

}
