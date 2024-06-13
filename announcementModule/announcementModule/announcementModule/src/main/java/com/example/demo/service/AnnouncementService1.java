package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.Employee;

public interface AnnouncementService1 {
	  Map<String, List<String>> getAnnouncements();

	    Employee addEmployee(Employee employee);
	    
	    String addAnnouncementMessage(String message);
}
