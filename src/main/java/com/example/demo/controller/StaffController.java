package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.OrganizationRepository;
import com.example.demo.dao.StaffRepository;
import com.example.demo.entity.Organization;
import com.example.demo.entity.Staff;
import com.example.demo.entity.StaffDTO;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
    private final org.slf4j.Logger logger= LoggerFactory.getLogger(StaffController.class);

	
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private OrganizationRepository orgRepository;
	
	@GetMapping("/getStaffByOrganization")
	public List<Staff> getStaffByOrganization() {
		/*
		 * Organization org= new Organization(); findByOrgName(org.getOrgName());
		 */
		return staffRepository.findAll();
	}
	////getStaff only
	@GetMapping("/getStaff/{id}")
	public StaffDTO getStaffOrg(@PathVariable int id) {
		Staff staff = staffRepository.findById(id).get();
		StaffDTO staffDto= new StaffDTO();
		staffDto.setStaffId(id);
		staffDto.setStaffName(staff.getStaffName());
		Organization organization= new Organization();
		organization.setOrgName(staff.getOrganization().getOrgName());
		staffDto.setOrganization(organization);
		return staffDto;
		
	}
	
	//7. We can delete staff by name,id
	@DeleteMapping("/deleteStaff/{id}")
	public void deleteStaff(@PathVariable int id) {
		
		Optional<Staff> staffId = staffRepository.findById(id);
		if(staffId.isPresent()) {
			Staff staff = staffId.get();
	        staff.setOrganization(null);
			System.out.println("deleteing staff with "+id);
			staffRepository.deleteById(id);
			System.out.println("testing is done....");
		}
	}
	
	@Transactional
	@DeleteMapping("/delete/{name}")
	public void deleteStaffByName(@PathVariable String name) {
		staffRepository.deleteByStaffName(name);
	}

	//update Staff and Organization Only
	@PutMapping("/updateStaff/{id}")
	public Staff updateStaffAndOrganizationOnly(@PathVariable int id, @RequestBody Staff staff) {
		Optional<Staff> staffId = staffRepository.findById(id);
       if(staffId.isPresent()) {
    	   Staff staff2 = staffId.get();
    	   staff2.setStaffName(staff.getStaffName());
    	   Organization org2= new Organization();
    	   org2.setOrgId(staff2.getOrganization().getOrgId());
    	   org2.setOrgName(staff2.getStaffName());
    	   return staffRepository.save(staff2);
          }
       else { 
    	  return staffRepository.save(staff);
       }
		
	}
	
	
}
	

