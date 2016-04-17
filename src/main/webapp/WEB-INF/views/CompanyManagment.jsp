<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>  
	<title>ElifTechTestAssignment</title>  
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.css">
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body ng-app="myApp" class="ng-cloak">
	<div class="container" ng-controller="CompanyController as ctrl">
		<div class="panel panel-default">
			<div class="panel-heading"><span class="lead">Company Form </span></div>
			<div>
				<form ng-submit="ctrl.submit()" name="companyForm" class="form-horizontal">
					<input type="hidden" ng-model="ctrl.company.id" />
					
						<div class="form-group">
							<label class="col-md-2 control-lable" for="companyName">Name</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.company.name" id="companyName" class="form-control input-sm" 
									placeholder="Enter company name" required ng-minlength="3"/>
								<div class="has-error" ng-show="companyForm.$dirty">
									<span ng-show="companyForm.companyName.$error.required">This is a required field</span>
									<span ng-show="companyForm.companyName.$error.minlength">Minimum length required is 3</span>
									<span ng-show="companyForm.companyName.$invalid">This field is invalid </span>
								</div>
							</div>
						</div>
					
	                         
	                       
					
						<div class="form-group">
							<label class="col-md-2 control-lable" for="estimatedEarnings">Estimated Earnings</label>
							<div class="col-md-7">
								<input type="text" ng-model="ctrl.company.estimatedEarnings" id="estimatedEarnings" class="form-control input-sm" 
									placeholder="Enter estimated earnings" />
							</div>
						</div>
				
	 
					
						<div class="form-actions floatRight">
							<input type="submit"  value="{{!ctrl.company.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="companyForm.$invalid">
								<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="companyForm.$pristine">Reset Form</button>
						</div>
					
				</form>
			</div>
		</div>
		
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading"><span class="lead">List of Companies </span></div>
			<div>
				<script type="text/ng-template"  id="tree_item_renderer.html">
					<span ng-bind="company.id"></span> | 
					<span ng-bind="company.name"></span> | 
					<span ng-bind="company.estimatedEarnings"></span> | 
					<span ng-bind="company.totalEstimatedEarnings"></span>
					<button type="button " ng-click="ctrl.add(company.id)" class="btn btn-success btn-xs">Add</button>
					<button type="button" ng-click="ctrl.edit(company)" class="btn btn-success btn-xs">Edit</button> 
					<button type="button" ng-click="ctrl.remove(company.id)" class="btn btn-danger btn-xs">Remove</button>
					<ul>
						<li ng-repeat="company in company.subCompany" ng-include="'tree_item_renderer.html'"></li>
					</ul>
				</script>

				<ul >
    				<li ng-repeat="company in ctrl.companies" ng-include="'tree_item_renderer.html'"></li>
				</ul>
			</div>
		</div>
		
	</div>
       
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
	<script src="<c:url value='/static/js/app.js' />"></script>
	<script src="<c:url value='/static/js/service/company_service.js' />"></script>
	<script src="<c:url value='/static/js/controller/company_controller.js' />"></script>
</body>
</html>