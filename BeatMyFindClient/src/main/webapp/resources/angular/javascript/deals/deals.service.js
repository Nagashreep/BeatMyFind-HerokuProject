(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.service('DealsService',DealsService);
	
	DealsService.$inject = ['$http', 'basePath']
	function DealsService($http, basePath){
		
		var dealsService = this;
		
		dealsService.find = function(dealsSearchModel){
			console.log("inside deals service");
			
			return $http({
				method:'POST',
				url: (basePath+'/fetchDeals'),
				data: dealsSearchModel
			}).then(function(response){
				console.log("response in deals service: "+response.data);
				return response;
			});
		}
	}
	
})();