package com.maftey.ElifTechTestAssignment.service;

import java.util.List;

import com.maftey.ElifTechTestAssignment.model.Company;

public interface CompanyService {
	Company findById(long id);
	void save(Company company);
	void update(Company company);
	void deleteById(long id);
	List<Company> findAll(); 
	boolean isExist(Company company);
}
