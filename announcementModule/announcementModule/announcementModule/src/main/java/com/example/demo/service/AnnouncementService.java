/*
 * package com.example.demo.service;
 * 
 * 
 * import java.time.format.DateTimeFormatter; import java.util.ArrayList; import
 * java.util.HashMap; import java.util.List; import java.util.Map;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.stereotype.Service;
 * 
 * import com.example.demo.entity.Employee; import
 * com.example.demo.repository.EmployeeRepository;
 * 
 * @Service
 * 
 * public class AnnouncementService {
 * 
 * @Autowired EmployeeRepository er;
 * 
 * public Map<String, List<String>> getAnnouncements() {
 * System.out.println(er.findAll()); // LocalDate today = LocalDate.now(); //
 * LocalDate startDate = today.plusDays(1); // LocalDate endDate =
 * today.plusDays(20);
 * 
 * Map<String, List<String>> announcements = new HashMap<>();
 * 
 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd");
 * List<Employee> upcomingBirthdays = er.needTheLast20DaysBirthDayRecords();
 * System.out.println("upcomingBirthdays:"+upcomingBirthdays); List<Employee>
 * onLeave = er.findByOnLeaveTrue(); List<Employee> newJoiners =
 * er.findByNewJoinerTrue();
 * 
 * announcements.put("upcomingBirthdays", upcomingBirthdays.stream() .map(e ->
 * e.getName() + " has a birthday on " + e.getBirthday().format(formatter))
 * .toList()); announcements.put("onLeave", onLeave.stream().map(e ->
 * e.getName() + " is on leave today.").toList());
 * announcements.put("importantNotes", er.findAll().stream() .filter(e ->
 * e.getImportantNote() != null && !e.getImportantNote().isEmpty())
 * .map(Employee::getImportantNote) .toList()); announcements.put("newJoiners",
 * newJoiners.stream().map(e -> "Welcome our new joiner, " + e.getName() +
 * "!").toList()); return announcements;
 * 
 * }
 * 
 * public Employee addEmployee(Employee employee) { return er.save(employee); }
 * 
 * 
 * 
 * }
 */





package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;

@Service
public class AnnouncementService {

    private List<String> announcementMessages = new ArrayList<>();

    public Map<String, List<String>> getAnnouncements() {
        Map<String, List<String>> announcements = new HashMap<>();
        List<String> todayAnnouncements = new ArrayList<>();
        todayAnnouncements.add("Today's Announcement 1");
        todayAnnouncements.add("Today's Announcement 2");
        announcements.put("today", todayAnnouncements);
        return announcements;
    }

    public Employee addEmployee(Employee employee) {
        System.out.println("Employee added: " + employee);
        return employee;
    }

    public String addAnnouncementMessage(String message) {
        announcementMessages.add(message);
        Employee e=new Employee()
;
        e.setAnnouncements(message);
        System.out.println("Announcement message added: " + message);
        return e + message;
    }

    public List<String> getAnnouncementMessages() {
    	 Employee e=new Employee();
    			         e.getAnnouncements();
        System.out.println("Retrieving announcement messages: " + announcementMessages);
        return new ArrayList<>(announcementMessages);
    }
}
