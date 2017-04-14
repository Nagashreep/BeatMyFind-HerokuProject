(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.service('EditorialService',EditorialService);
	
	EditorialService.$inject = ['$http','basePath']
	function EditorialService($http, basePath){
		var editorialService = this;
		
		editorialService.publish = function(editorial){
			return $http ({
				method:'POST',
				url: (basePath+'/publishEditorial'),
				data: editorial
			})
			.then(function(response){
				console.log("response in editorial service: "+response.data);
				return response;
			});
		}
		
		editorialService.retrieve = function(){
			return $http ({
				method:'GET',
				url: (basePath+'/retrieveEditorial'),
			})
			.then(function(response){
				console.log("response in editorial service: "+response.data);
				return response.data;
			});
		}
		
	}
})();