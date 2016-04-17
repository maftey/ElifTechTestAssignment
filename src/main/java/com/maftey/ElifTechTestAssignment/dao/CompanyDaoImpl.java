package com.maftey.ElifTechTestAssignment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.maftey.ElifTechTestAssignment.model.Company;

@Repository
public class CompanyDaoImpl implements CompanyDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public Company findById(long id) {
		return getSession().get(Company.class, id);
	}

	@Override
	public void save(Company company) {
		getSession().persist(company);
	}

	@Override
	public void update(Company company) {
		getSession().merge(company);
		
	}

	@Override
	public void delete(Company company) {
		getSession().delete(company);
		
	}
	
	@Override
	public void deleteById(long id) {
		Company company = findById(id);
		if(company != null) {
			delete(company);
		}
		
	}

	@Override
	public List<Company> findAll() {
		return getSession().createCriteria(Company.class).list();
	}

	@Override
	public List<Company> findAllTopCompany() {
		String hql = "FROM Company company WHERE company.parentId = null";
		return getSession().createQuery(hql).list();
	}
	
}
