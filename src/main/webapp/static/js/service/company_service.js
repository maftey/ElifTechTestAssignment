'use strict';
 
App.factory('CompanyService', ['$http', '$q', function($http, $q){
 
	return {
         
		fetchAllCompanies: function() {
			return $http.get('company/')
				.then(
					function(response){
						return response.data;
                    }, 
                    function(errResponse){
                    	console.error('Error while fetching companies');
                    	return $q.reject(errResponse);
                    }
				);
		},
             
		createCompany: function(company){
			return $http.post('company/', company)
				.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while creating company');
						return $q.reject(errResponse);
					}
				);
		},
             
		updateCompany: function(company, id){
			return $http.put('company/'+id, company)
				.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while updating company');
						return $q.reject(errResponse);
					}
				);
		},
             
		deleteCompany: function(id){
			return $http.delete('company/'+id)
				.then(
					function(response){
						return response.data;
					}, 
					function(errResponse){
						console.error('Error while deleting company');
						return $q.reject(errResponse);
					}
				);
		}
         
	};
 
}]);