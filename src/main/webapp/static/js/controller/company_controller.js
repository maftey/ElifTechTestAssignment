'use strict';
 
App.controller('CompanyController', ['$scope', 'CompanyService', function($scope, CompanyService) {
	var self = this;
	self.company = {id:null, name:'', parentId:'', estimatedEarnings:0, totalEstimatedEarnings: 0};
	self.companies = [];
               
	self.fetchAllCompanies = function() {
		CompanyService.fetchAllCompanies()
			.then(
				function(data) {
					self.companies = data;
				},
				function(errResponse){
					console.error('Error while fetching Companies');
				}
			);
	};
            
	self.createCompany = function(company){
		CompanyService.createCompany(company)
			.then(
				self.fetchAllCompanies, 
				function(errResponse){
					console.error('Error while creating Company.');
				} 
			);
	};
 
	self.updateCompany = function(company, id){
		CompanyService.updateCompany(company, id)
			.then(
				self.fetchAllCompanies, 
					function(errResponse){
						console.error('Error while updating Company.');
					} 
			);
	};
 
	self.deleteCompany = function(id){
		CompanyService.deleteCompany(id)
			.then(
				self.fetchAllCompanies, 
				function(errResponse){
					console.error('Error while deleting Company.');
				} 
			);
	};
 
	self.fetchAllCompanies();
 
	self.submit = function() {
		if(self.company.id===null){
			console.log('Saving New Company', self.company);    
			self.createCompany(self.company);
		}else{
			self.updateCompany(self.company, self.company.id);
			console.log('Company updated with id ', self.company.id);
		}
		self.reset();
	};
	
	self.add = function(parentId){
		self.reset();
		self.company.parentId = parentId;
		self.focus("companyName");
	};
	
	self.edit = function(company){
		self.company = angular.copy(company);
		self.focus("companyName");
	};
	
	self.focus  = function(elName){
		document.getElementById(elName).focus();
	};
               
	self.remove = function(id){
		console.log('id to be deleted', id);
		if(self.company.id === id) {
			self.reset();
		}
		self.deleteCompany(id);
	};
 
           
	self.reset = function(){
		self.company = {id:null, companyName:'', parentId:'', estimatedEarnings:'0', totalEstimatedEarnings: '0'};
		$scope.companyForm.$setPristine(); //reset Form
	};
 
}]);