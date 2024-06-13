package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;

@Service
public class AnnouncementServiceImpl implements AnnouncementService1 {

    private Map<String, List<String>> announcements = new HashMap<>();

    @Override
    public Map<String, List<String>> getAnnouncements() {
        // Sample implementation, replace with actual logic
        if (announcements.isEmpty()) {
            announcements.put("announcements", List.of("Announcement 1", "Announcement 2"));
        }
        return announcements;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        // Sample implementation, replace with actual logic to save the employee
        return employee;
    }

    @Override
    public String addAnnouncementMessage(String message) {
        announcements.computeIfAbsent("announcements", k -> new ArrayList<>()).add(message);
        return "Announcement added successfully";
    }
   
    
}
