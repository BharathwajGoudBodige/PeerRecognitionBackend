package com.org.peerrecognition.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.peerrecognition.dto.EmployeeDto;
import com.org.peerrecognition.exception.EmployeesNotFoundException;
import com.org.peerrecognition.exception.ResourceNotFoundException;
import com.org.peerrecognition.model.Employee;
import com.org.peerrecognition.repository.EmployeeRepository;
import com.org.peerrecognition.service.EmployeeService;
import com.org.peerrecognition.util.NumberUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Autowired
	private ModelMapper modelMapper;

//	@Override
//	public List<EmployeeDto> searchEmployeeByName(String employeeName) {
//		List<Employee> employees = this.employeeRepo.findByEmployeeNameContaining(employeeName);
//		List<EmployeeDto> employeeList = employees.stream()
//				.map((employee) -> this.employeeToDto(employee))
//				.collect(Collectors.toList());
//		if(employeeList.isEmpty()) throw new EmployeesNotFoundException(employeeName);
//		return employeeList;
//	}
	
//	@Override
//	public List<EmployeeDto> searchEmployeeById(int employeeId) {
//		String eId = String.valueOf(employeeId);
//		List<Employee> employees = this.employeeRepo.findByEmployeeIdContaining(eId);
//		List<EmployeeDto> searchedEmployees = employees.stream()
//				.map((employee) -> this.employeeToDto(employee))
//				.collect(Collectors.toList());
//		if(searchedEmployees.isEmpty()) throw new EmployeesNotFoundException(employeeId);
//		return searchedEmployees;
//	}
	
	@Override
	public EmployeeDto getEmployeeById(int employeeId) {
		Employee receivedEmployee = this.employeeRepo.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee","Id",employeeId));
		return this.employeeToDto(receivedEmployee);
	}
	
	public EmployeeDto employeeToDto(Employee employee)
	{
		return this.modelMapper.map(employee, EmployeeDto.class);
	}
	
	public Employee dtoToEmployee(EmployeeDto employeeDto) {
		return this.modelMapper.map(employeeDto, Employee.class);
	}

	@Override
	public List<EmployeeDto> searchEmployee(String employeeName) {
		if(NumberUtils.isInteger(employeeName))
		{	
			List<Employee> employeesByIds = this.employeeRepo.findByEmployeeIdContaining(employeeName);
			List<EmployeeDto> searchedEmployeesByIds = employeesByIds.stream()
					.map((employee) -> this.employeeToDto(employee))
					.collect(Collectors.toList());
			if(searchedEmployeesByIds.isEmpty()) throw new EmployeesNotFoundException(employeeName);
			return searchedEmployeesByIds;
		}
		else
		{
			List<Employee> employeesByName = this.employeeRepo.findByEmployeeNameContaining(employeeName);
			List<EmployeeDto> searchedEmployeesByNames = employeesByName.stream()
					.map((employee) -> this.employeeToDto(employee))
					.collect(Collectors.toList());
			if(searchedEmployeesByNames.isEmpty()) throw new EmployeesNotFoundException(employeeName);
			return searchedEmployeesByNames;
		}
		
	}


}
