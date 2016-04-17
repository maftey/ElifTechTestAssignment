package com.maftey.ElifTechTestAssignment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.maftey.ElifTechTestAssignment.model.Company;
import com.maftey.ElifTechTestAssignment.service.CompanyService;

@RestController
public class CompanyRestController {
	  
	@Autowired
	CompanyService companyService;
  
	@RequestMapping(value = "/company/", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Company> listAllCompanies() {
		List<Company> Companies = companyService.findAll();

		return Companies;
	}
  
	@RequestMapping(value = "/company/", method = RequestMethod.POST)
	public ResponseEntity<Void> createCompany(@RequestBody Company company,    UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Company " + company.getName());
  
		if (companyService.isExist(company)) {
			System.out.println("A Company with name " + company.getName() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
  
		companyService.save(company);
  
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/company/{id}").buildAndExpand(company.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/company/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Company> updateCompany(@PathVariable("id") long id, @RequestBody Company company) {
		System.out.println("Updating Company " + id);
          
		Company currentCompany = companyService.findById(id);
          
		if (currentCompany==null) {
			System.out.println("Company with id " + id + " not found");
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		}
		
		currentCompany.setEstimatedEarnings(company.getEstimatedEarnings());
		currentCompany.setName(company.getName());
		currentCompany.setParentId(company.getParentId());//ToDo: check parent id
          
		companyService.update(currentCompany);
		return new ResponseEntity<Company>(currentCompany, HttpStatus.OK);
	}
  
      
	@RequestMapping(value = "/company/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Company> deleteCompany(@PathVariable("id") long id) {
		System.out.println("Fetching & Deleting Company with id " + id);
  
		Company company = companyService.findById(id);
		if (company == null) {
			System.out.println("Unable to delete. Company with id " + id + " not found");
			return new ResponseEntity<Company>(HttpStatus.NOT_FOUND);
		}
  
		companyService.deleteById(id);
		return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
	}
  
}
