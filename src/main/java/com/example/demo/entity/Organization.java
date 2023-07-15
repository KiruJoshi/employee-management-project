package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name = "organization")
@Entity

public class Organization {

	@Id
	@Column(name = "org_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orgId;

	@Column(name = "org_name")
	private String orgName;

//	@JsonIgnoreProperties("organization")
	@JsonIgnore
	//@JsonManagedReference
	@OneToMany(cascade = { CascadeType.MERGE}, mappedBy = "organization", fetch = FetchType.EAGER)
	private List<Staff> staff;

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<Staff> getStaff() {
		return staff;
	}

	public void setStaff(List<Staff> staff) {
		this.staff = staff;
	}

	@Override
	public String toString() {
		return "Organization [orgId=" + orgId + ", orgName=" + orgName + "]";
	}

}
