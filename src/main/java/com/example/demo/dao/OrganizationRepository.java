package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Integer>{

	public Organization findByOrgId(int id);

}
