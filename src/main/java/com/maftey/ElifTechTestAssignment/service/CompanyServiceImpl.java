package com.maftey.ElifTechTestAssignment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maftey.ElifTechTestAssignment.dao.CompanyDao;
import com.maftey.ElifTechTestAssignment.model.Company;

@Transactional
@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDao companyDao;
	
	@Override
	public Company findById(long id) {
		return companyDao.findById(id);
	}

	@Override
	public void save(Company company) {
		companyDao.save(company);
	}

	@Override
	public void update(Company company) {
		companyDao.update(company);
	}

	@Override
	public void deleteById(long id) {
		companyDao.deleteById(id);
	}

	@Override
	public List<Company> findAll() {
		List<Company> companies = companyDao.findAllTopCompany();
		for(Company company : companies) {
			company.getTotalEstimatedEarnings();
		}
		
		return companies;
	}

	@Override
	public boolean isExist(Company company) {
		if(company.getId() == null) {
			return false;
		}
		company = findById(company.getId());
		return company != null;
	}
}
