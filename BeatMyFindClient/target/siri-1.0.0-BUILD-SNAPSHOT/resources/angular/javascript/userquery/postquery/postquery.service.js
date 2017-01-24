(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.service('PostQueryService', PostQueryService);
	
	PostQueryService.$inject = ['$http', 'basePath']
	function PostQueryService($http, basePath){
		
		var postQueryService = this;
		
		postQueryService.postQuery = function(userQuery){
			console.log("inside postQuery service");
			return $http({
				method: 'POST',
				url: (basePath+'/addUserQuery'),
				data: userQuery
			})
			.then(function(response){
				return response.data;
			})
		};
		
	}
})();