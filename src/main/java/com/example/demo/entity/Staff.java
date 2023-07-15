package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name="staff")
@Entity
public class Staff {


	@Id
    @Column(name="staff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffId;

    @Column(name="staff_name")
    private String staffName;
    
   //@JsonIgnore
  // @JsonBackReference
	@ManyToOne(fetch=FetchType.EAGER,cascade = {CascadeType.MERGE,CascadeType.REMOVE})//forcefully get data from organization in eager
    @JoinColumn(name = "orgId")
    private Organization organization;
    
    @JsonIgnore
   // @JsonBackReference
	@OneToMany(	cascade = {CascadeType.REMOVE,CascadeType.MERGE}, 
	mappedBy = "staff",fetch =FetchType.EAGER)
    private List<Employee> employee;


	public List<Employee> getEmployee() {
		return employee;
	}


	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}



	public int getStaffId() {
		return staffId;
	}


	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}


	public String getStaffName() {
		return staffName;
	}


	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}


	public Organization getOrganization() {
		return organization;
	}


	public void setOrganization(Organization organization) {
		this.organization = organization;
	}


	@Override
	public String toString() {
		return "Staff [staffId=" + staffId + ", staffName=" + staffName + "]";
	}


	
    
    
}