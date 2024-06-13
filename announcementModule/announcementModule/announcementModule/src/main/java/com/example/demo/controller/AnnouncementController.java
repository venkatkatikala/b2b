package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.AnnouncementService;
import com.example.demo.service.AnnouncementServiceImpl;

@RestController
@RequestMapping("/announcements")

public class AnnouncementController {
	@Autowired
	AnnouncementService as;
	
	@Autowired
	AnnouncementServiceImpl asi;

	  @GetMapping("/today")
	    public Map<String, List<String>> getAnnouncements() {
	        return as.getAnnouncements();
	    }

	    @PostMapping("/insert")
	    public Employee addEmployee(@RequestBody Employee employee) {
	        return as.addEmployee(employee);
	    }
	   
	    @PostMapping("/addMessage")
	    public String addAnnouncementMessage(@RequestBody String message) {
	        return asi.addAnnouncementMessage(message);
	    }
	    
	    @GetMapping("/messages")
	    public List<String> getAnnouncementMessages() {
	        return as.getAnnouncementMessages();
	    }
}

