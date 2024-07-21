package com.org.peerrecognition.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.peerrecognition.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
