package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Organization;
import com.example.demo.entity.Staff;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {

	
	public void deleteByStaffName(String staffName);

}
