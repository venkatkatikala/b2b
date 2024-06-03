package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.LeaveBalance;
import java.util.List;
import com.example.entity.User;



@Repository
public interface LeaveBalanceDao extends JpaRepository<LeaveBalance, Integer> {

	List<LeaveBalance> findByEmpnumber(String empnumber);
	LeaveBalance findByEmpnumberAndLeaveType(String empnumber, String leaveType);
	
}
