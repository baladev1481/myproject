package com.java.training.microservice.controllers;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.training.microservice.exceptions.ResourceNotFoundException;
import com.java.training.microservice.model.Employee;
import com.java.training.microservice.repository.EmployeeRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository empRepository;
	
	@PostMapping(path = "/addemployee")
	public Employee addEmployee(@RequestBody Employee emplpoyee) {
		return empRepository.save(emplpoyee);
	}
	
	@GetMapping(path = "/allemployees")
	public List<Employee> allEmployees() {
		return empRepository.findAll();
	}
	
	@GetMapping(path="/employee/{id}")
    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Long employeeId)
    throws ResourceNotFoundException {
        Employee employee = empRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }
	
	 @PutMapping(path="/updateemployee/{id}")
	    public ResponseEntity <Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
	      @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
	        Employee employee = empRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

	        employee.setEmpId(employeeDetails.getEmpId());
	        employee.setFirstName(employeeDetails.getFirstName());
	        employee.setLastName(employeeDetails.getLastName());
	        employee.setSalary(employeeDetails.getSalary());
	        employee.setDoj(employeeDetails.getDoj());
	        employee.setDeptId(employeeDetails.getDeptId());
	        
	        final Employee updatedEmployee = empRepository.save(employee);
	        return ResponseEntity.ok(updatedEmployee);
     }
	 
	 @DeleteMapping("/deleteemployee/{id}")
	 public Map<String, String> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = empRepository.findById(employeeId).orElseThrow(
				() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		empRepository.delete(employee);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "Employee deleted successfully");
		return response;
	 }
}
