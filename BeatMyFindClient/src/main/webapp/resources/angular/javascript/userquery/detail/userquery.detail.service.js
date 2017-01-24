(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.service('UserQueryDetailService',UserQueryDetailService);
	
	UserQueryDetailService.$inject = ['$http','basePath']
	function UserQueryDetailService($http,basePath){
		
		var queryDetailService = this;
		
		queryDetailService.addComment = function(userComment){
			return $http({
				method:'POST',
				url: (basePath+'/addUserComment'),
				data: userComment
			})
			.then(function(response){
				console.log("response in detail service: "+response.data);
				return response;
			});
		}
		
	}
})();