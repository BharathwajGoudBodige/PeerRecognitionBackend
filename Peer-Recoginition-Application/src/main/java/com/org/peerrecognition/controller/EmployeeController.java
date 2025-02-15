package com.org.peerrecognition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.peerrecognition.dto.EmployeeDto;
import com.org.peerrecognition.dto.EmployeeRecognitionDto;
import com.org.peerrecognition.service.EmployeeService;

@RequestMapping("/api")
@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
//	@GetMapping("/search/employee/name/{employeeName}")
//	public ResponseEntity<List<EmployeeDto>> searchEmployees(@PathVariable String employeeName)
//	{
//		List<EmployeeDto> searchedEmployees = this.employeeService.searchEmployeeByName(employeeName);
//		return ResponseEntity.ok(searchedEmployees);
//	}
	
	@GetMapping("/{employeeId}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int employeeId)
	{
		EmployeeDto receivedEmployee = this.employeeService.getEmployeeById(employeeId);
		return ResponseEntity.ok(receivedEmployee);
	}
	
//	@GetMapping("/search/employee/id/{employeeId}")
//	public ResponseEntity<List<EmployeeDto>> searchEmployeesById(@PathVariable int employeeId)
//	{
//		List<EmployeeDto> searchedEmployees = this.employeeService.searchEmployeeById(employeeId);
//		return ResponseEntity.ok(searchedEmployees);
//	}
	
	@GetMapping("/search/employee/{keyword}")
	public ResponseEntity<List<EmployeeDto>> searchEmployeesById(@PathVariable String keyword)
	{
		List<EmployeeDto> searchedEmployees = this.employeeService.searchEmployee(keyword);
		return ResponseEntity.ok(searchedEmployees);
	}
	
	@GetMapping("/get/earnedrecognitions/{receiverId}")
	public ResponseEntity<EmployeeRecognitionDto> getEarnedBadges(@PathVariable int receiverId)
	{
		EmployeeRecognitionDto badgesEarned = this.employeeService.getRecognitions(receiverId);
		return new ResponseEntity<>(badgesEarned, HttpStatus.OK);
		
	}

}
