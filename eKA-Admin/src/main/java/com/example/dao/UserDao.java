package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.User;
import java.util.List;



@Repository
public interface UserDao extends JpaRepository<User, Integer>{
	User findByEmailAndPasswordAndRole(String email, String password, String role);
	User findByEmail(String email);
	List<User> findByRole(String role);
	//List<User> findByDesignation(String designation);
	User  findByEmpNumber(Long empNumber);
	
	User findByEmailAndRole(String email, String role);
}
