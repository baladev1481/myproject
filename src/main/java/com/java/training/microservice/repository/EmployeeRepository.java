package com.java.training.microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.training.microservice.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
