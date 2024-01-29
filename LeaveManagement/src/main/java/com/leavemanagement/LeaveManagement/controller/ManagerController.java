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

import com.leavemanagement.LeaveManagement.payload.LeaveDto;
import com.leavemanagement.LeaveManagement.payload.StatusDto;
import com.leavemanagement.LeaveManagement.services.LeaveService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/manager/leave")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired
	private LeaveService leaveService;

	//Get method to retrieve all the pending methods
	@GetMapping("/pending")
	public ResponseEntity<List<LeaveDto>> getPendingLeaves(){
		logger.info("Received request to /manager/leave/pending");
		List<LeaveDto> leaveDto = this.leaveService.getPendingLeaves();
		return new ResponseEntity<>(leaveDto,HttpStatus.OK);
	}
	
	//Get method to retreive all users leaves
	@GetMapping("/leaves")
	public ResponseEntity<List<LeaveDto>> getAllLeaves(){
		logger.info("Received request to /manager/leave/leaves");
		List<LeaveDto> leaveDto = this.leaveService.getAllLeaves();
		return new ResponseEntity<>(leaveDto,HttpStatus.OK);
	}
	
	//put method to approve or reject leave
	@PutMapping("/setstatus/{id}")
	public ResponseEntity<LeaveDto> approveLeave(@Valid @RequestBody StatusDto statusDto,@PathVariable Integer id){
		logger.info("Received request to /manager/leave/setstatus");
		LeaveDto leaveDto = this.leaveService.setStatus(statusDto,id);
		return new ResponseEntity<>(leaveDto,HttpStatus.OK);
	}
	
	
}

