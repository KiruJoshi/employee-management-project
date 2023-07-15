package com.example.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;
import com.example.demo.entity.EmployeeDTO;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	
	@GetMapping("/getEmployeeByOrganization")
	public List<Employee> getEmployeeByOrganization(){
		
		return employeeRepository.findAll();
		
	}
	
	@GetMapping("/getEmployeeByOrgnanization/{id}")
	public EmployeeDTO getEmployeeByOrgnanization(@PathVariable int id) {
		Optional<Employee> findById = employeeRepository.findById(id);
		EmployeeDTO empDTO= new EmployeeDTO();

		if(findById.isPresent()) {
			Employee employee = findById.get();
			empDTO.setEmpId(id);
			empDTO.setEmpName(employee.getEmpName());
			empDTO.setOrgName(employee.getStaff().getOrganization().getOrgName());
		}
		return empDTO;
	}
	
	
	
	@PutMapping("/updateEmployee/{id}")
	public Employee updateEmployeeOnly(@PathVariable int id, @RequestBody Employee emp) {
		
		Optional<Employee> findById = employeeRepository.findById(id);
		if(findById.isPresent()) {
			Employee employee = findById.get();
			employee.setEmpName(emp.getEmpName());
			employee.setEmpCompany(emp.getEmpCompany());
			employee.setStaff(findById.get().getStaff());//foreign key
			return employeeRepository.save(employee);
		}
		else {
			return null;
		}
	}
	
	
	
	@DeleteMapping("/deleteEmployee/{id}")
	public void deleteEmployee(@PathVariable int id){
		Optional<Employee> findById = employeeRepository.findById(id);
		if(findById.isPresent()) {
			Employee employee = findById.get();
			employee.setStaff(null);
			System.out.println("delete Employee...");

			employeeRepository.deleteById(id);
			System.out.println("delete Employee...");

		}
		
	}
}








