package com.announments.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.announments.entity.User;


@Repository
public interface UserDao extends JpaRepository<User, Integer> {

	User findByEmpnumber(long empnumber);
	   @Query("SELECT u FROM User u WHERE u.dob BETWEEN :startDate AND :endDate")
	    List<User> findUsersWithBirthdaysBetween(LocalDate startDate, LocalDate endDate);
	 
	 @Query("SELECT u FROM User u WHERE u.dateofjoining BETWEEN :startDate AND :endDate")
	    List<User> findUsersWithJoiningDatesBetween(LocalDate startDate, LocalDate endDate);
	 
	 User findByEmail(String email);
}
