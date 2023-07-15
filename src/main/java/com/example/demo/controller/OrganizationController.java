package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.OrganizationRepository;
import com.example.demo.dao.StaffRepository;
import com.example.demo.entity.Employee;
import com.example.demo.entity.Organization;
import com.example.demo.entity.OrganizationDTO;
import com.example.demo.entity.Staff;

@RestController
public class OrganizationController {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(OrganizationController.class);

	@Autowired
	private OrganizationRepository organizationRepository;

	@Autowired

	private StaffRepository staffRepository;

	@PostMapping("/save-org")
	public Organization saveOrganization(@RequestBody Organization organization) {
		logger.info("Saving Organization Details from Controller Layer :" + organization);
		Optional<Organization> findById = organizationRepository.findById(organization.getOrgId());
		if (findById.isPresent()) {
			for (Staff staff : organization.getStaff()) {
				staff.setOrganization(organization);

			}
			return organizationRepository.save(organization);
		} else {
			for (Staff staff : organization.getStaff()) {
				staff.setOrganization(organization);
				for (Employee emp : staff.getEmployee()) {
					emp.setStaff(staff);
				}
			}
			return organizationRepository.save(organization);

		}
	}

	// 8. We can update Employee, staff, organization.
	@PutMapping("/updateStaffOrgEmp/{id}")
	public Organization updateAllTheThree(@RequestBody Organization org, @PathVariable int id) {

		Optional<Organization> findById = organizationRepository.findById(org.getOrgId());
		if (findById.isPresent()) {
			for (Staff staff : org.getStaff()) {
				staff.setOrganization(org);// foreign key setup
				for (Employee emp : staff.getEmployee()) {
					emp.setStaff(staff);
				}
			}
			return organizationRepository.save(org);
		} else {
			return null;

		}
	}

	// Update Organization only
	@PutMapping("/updateOrganization/{id}")
	public Organization updateOrganization(@PathVariable int id, @RequestBody Organization org) {

		Optional<Organization> findById = organizationRepository.findById(org.getOrgId());
		if (findById.isPresent()) {
			for (Staff staff : org.getStaff()) {

				staff.setOrganization(org);
			}
			return organizationRepository.save(org);

		} else {
			return null;
		}

	}

	@GetMapping("/get-org")
	public List<Organization> getOrganizationsDetails() {
		logger.info("Getting Organization Details from Controller Layer :");
System.out.println("Testing get method");
		return organizationRepository.findAll();
	}

	@GetMapping("/getStaffByOrganization/{id}")
	public OrganizationDTO getStaffByOrganizationDTO(@PathVariable int id) {
		Optional<Staff> findByStaffId = staffRepository.findById(id);
		OrganizationDTO orgnDto = new OrganizationDTO();

		if (findByStaffId.isPresent()) {
			Staff staff = findByStaffId.get();
			orgnDto.setStaffID(id);
			orgnDto.setStaffName(staff.getStaffName());
			orgnDto.setOrgName(staff.getOrganization().getOrgName());
		}

		return orgnDto;
	}

}
