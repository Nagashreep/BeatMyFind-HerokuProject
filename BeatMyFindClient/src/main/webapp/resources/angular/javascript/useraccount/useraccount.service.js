(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.service('UserAccountService',UserAccountService);
	
	UserAccountService.$inject = ['CommonService','$http','basePath']
	function UserAccountService(CommonService, $http, basePath){
		var userAccountService = this;
		
		userAccountService.fetchUserQueries = function(){
			var userId = CommonService.getUserId();
			console.log('userID: '+userId+' basePath:'+basePath);
			
			return $http({
				method: 'POST',
				url: (basePath+'/fetchUserQueries'),
				data: userId
			}).then(function(response){
				return response.data
			});
		}
		
		userAccountService.closeQueries = function(queryIdsToClose){
			return $http({
				method: 'POST',
				url:(basePath+'/closeUserQueries'),
				data: queryIdsToClose
			}).then(function(response){
				return response
			});
		}
		
		userAccountService.fetchMyFavQueries = function(){
			console.log("Function to be implemented");
		}
	}
	
})();