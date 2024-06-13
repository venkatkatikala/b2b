package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    EmployeeRepository er;

    @Override
    public Map<String, List<String>> getAnnouncements() {
        LocalDate today = LocalDate.now();
        // Additional date logic if needed

        Map<String, List<String>> announcements = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd");
        List<Employee> upcomingBirthdays = er.needTheLast20DaysBirthDayRecords();
        List<Employee> onLeave = er.findByOnLeaveTrue();
        List<Employee> newJoiners = er.findByNewJoinerTrue();

        announcements.put("upcomingBirthdays", upcomingBirthdays.stream()
                .map(e -> e.getName() + " has a birthday on " + e.getBirthday().format(formatter))
                .toList());
        announcements.put("onLeave", onLeave.stream().map(e -> e.getName() + " is on leave today.").toList());
        announcements.put("importantNotes", er.findAll().stream()
                .filter(e -> e.getImportantNote() != null && !e.getImportantNote().isEmpty())
                .map(Employee::getImportantNote)
                .toList());
        announcements.put("newJoiners", newJoiners.stream().map(e -> "Welcome our new joiner, " + e.getName() + "!").toList());

        return announcements;
    }

    @Override
    public List<Employee> getTodayBirthdays() {
        LocalDate today = LocalDate.now();
        return er.findByBirthdayBetween(today, today);
    }

    @Override
    public List<Employee> getNewJoiners() {
        return er.findByNewJoinerTrue();
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return er.save(employee);
    }
}
