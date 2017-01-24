(function(){
	"use strict";
	
	angular.module('beatMyFindApp')
	.service('UserQueryService',UserQueryService);
	
	UserQueryService.$inject = ['$http', 'basePath']
	function UserQueryService($http, basePath){
		var queryService = this;
		
		queryService.fetchOpenUserQueries = function(){
			console.log("inside service");
			return $http.get(basePath+'/fetchOpenQueries')
			.then(function(response){
				return response.data
			});
		}
		
		queryService.fetchClosedUserQueries = function(){
			console.log("inside service");
			return $http.get(basePath+'/fetchClosedQueries')
			.then(function(response){
				return response.data
			});
		}
		
		queryService.fetchExpiredUserQueries = function(){
			console.log("inside service");
			return $http.get(basePath+'/fetchExpiredQueries')
			.then(function(response){
				return response.data
			});
		}
		
		queryService.fetchQueryDetails = function(queryId){
			var config = {};
			console.log("inside service queryId: "+queryId);
			
			if(queryId){
				config.params = {'queryId':queryId};
			}
			
			return $http.get((basePath+'/fetchQueryDetails/'), config)
			.then(function(response){
				console.log("data: "+response.data);
				return response.data;
			});
		}
		
	}
})();