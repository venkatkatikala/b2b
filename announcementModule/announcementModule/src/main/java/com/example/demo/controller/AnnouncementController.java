package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Employee;
import com.example.demo.service.AnnouncementService;

@Controller
public class AnnouncementController {
    @Autowired
    AnnouncementService as;

    @GetMapping("/today")
    @ResponseBody
    public Map<String, List<String>> getAnnouncements() {
        return as.getAnnouncements();
    }

    @PostMapping("/insert")
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee employee) {
        return as.addEmployee(employee);
    }

    @GetMapping("/home")
    public String home() {
        return "home";  // returns home.jsp
    }

    @GetMapping("/announcements")
    public String announcements(Model model) {
        Map<String, List<String>> announcements = as.getAnnouncements();
        List<Employee> birthdays = as.getTodayBirthdays();
        List<Employee> newJoiners = as.getNewJoiners();

        model.addAttribute("announcements", announcements);
        model.addAttribute("birthdays", birthdays);
        model.addAttribute("newJoiners", newJoiners);

        return "announcements";  // returns announcements.jsp
    }
    @GetMapping("/addEmployee")
    public String addEmployee() {
        return "addEmployee";  // returns addEmployee.jsp
    }
}