package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.Employee;

public interface AnnouncementService {
    Map<String, List<String>> getAnnouncements();
    List<Employee> getTodayBirthdays();
    List<Employee> getNewJoiners();
    Employee addEmployee(Employee employee);
}
