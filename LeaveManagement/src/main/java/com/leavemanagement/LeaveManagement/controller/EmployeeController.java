package com.leavemanagement.LeaveManagement.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leavemanagement.LeaveManagement.exception.InvalidLeaveDatesException;
import com.leavemanagement.LeaveManagement.payload.LeaveDto;
import com.leavemanagement.LeaveManagement.services.LeaveService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee/leave")
@PreAuthorize("hasRole('EMPLOYEE')")
public class EmployeeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private LeaveService leaveService;
	
	//post method to create and applying for leave
	@PostMapping("/{userId}")
	public ResponseEntity<LeaveDto> createLeave(@Valid @RequestBody LeaveDto leaveDto,@PathVariable Integer userId) throws InvalidLeaveDatesException{
		logger.info("Received request to /employee/leave/userid");
		LeaveDto savedDto = this.leaveService.applyLeave(leaveDto, userId);
		return new ResponseEntity<>(savedDto,HttpStatus.CREATED);
	}
	
	//get method to get all the leaves by userid
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<LeaveDto>> getLeaveByUserId(@PathVariable Integer userId){
		logger.info("Received request to /employee/users/userid");
		List<LeaveDto> leaveDto = this.leaveService.getLeavesByEmployeeId(userId);
		return new ResponseEntity<>(leaveDto,HttpStatus.OK);
	}
	
	
}

