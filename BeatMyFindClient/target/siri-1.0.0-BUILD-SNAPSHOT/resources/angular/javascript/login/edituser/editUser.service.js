(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.service('NewUserRegistrationService',NewUserRegistrationService);
	
	NewUserRegistrationService.$inject = ['$http', 'basePath']
	function NewUserRegistrationService($http, basePath){
		var newUserService = this;
		
		newUserService.isUserNameUnique = function(userName){
			return $http({
				method: 'POST',
				url: (basePath+'/isUserNameUnique'),
				data: userName
			}).then(function(response){
				return response;
			});
		};
		
		newUserService.registerUser = function(user){
			return $http({
				method: 'POST',
				url: (basePath+'/saveUser'),
				data: user
			}).then(function(response){
				return response;
			});
		}
	}
	
})();