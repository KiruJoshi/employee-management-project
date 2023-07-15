package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.dao.EmployeeRepository;
import com.example.demo.entity.Employee;

@SpringBootApplication
public class EmployeeManagementProjectApplication implements CommandLineRunner{

	@Autowired
	private EmployeeRepository employeeRepository;
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee emp= new Employee();
		employeeRepository.save(emp);
		
	}

}

/*
 * 1. create crud for Employee (id and name will be unique), staff and
 * organization
 * 
 * 2. Fetch API for all entity
 * 
 * 3. We need to fetch all entity of staff by organization
 * 
 * 
 * 4. We need to fetch all entity of Employee by organization->employeeDTO
 * 
 * 5. We need to fetch all entity created by staff
 * 
 * 6. We can delete Employee by name,id
 * 
 * 7. We can delete staff by name,id
 * 
 * 8. We can update Employee, staff, organization.
 * 
 * 9. In Employee we need to show staff(Who has created), organization name.
 * 
 * 10. Add swagger, logs and proper error message for exceptions
 */