package com.org.peerrecognition.service;

import java.util.List;

import com.org.peerrecognition.dto.EmployeeDto;
import com.org.peerrecognition.dto.EmployeeRecognitionDto;

public interface EmployeeService {
	
	//List<EmployeeDto> searchEmployeeByName(String employeeName);
	
	EmployeeDto getEmployeeById(int employeeId);
	
	//List<EmployeeDto> searchEmployeeById(int employeeId);
	
	List<EmployeeDto> searchEmployee(String employeeName);
	
	EmployeeRecognitionDto getRecognitions(int employeeId);

}
