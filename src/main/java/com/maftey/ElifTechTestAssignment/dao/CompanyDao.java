package com.maftey.ElifTechTestAssignment.dao;

import java.util.List;

import com.maftey.ElifTechTestAssignment.model.Company;

public interface CompanyDao {
	Company findById(long id);
	void save(Company company);
	void update(Company company);
	void deleteById(long id);
	List<Company> findAllTopCompany();
	void delete(Company company);
	List<Company> findAll(); 
}
