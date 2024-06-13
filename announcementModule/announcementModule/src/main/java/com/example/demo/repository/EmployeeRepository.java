package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findByBirthdayBetween(LocalDate startDate, LocalDate endDate);
    List<Employee> needTheLast20DaysBirthDayRecords();
    List<Employee> findByOnLeaveTrue();
    List<Employee> findByNewJoinerTrue();
    //List<Employee> findByBirthday(LocalDate date);
}
