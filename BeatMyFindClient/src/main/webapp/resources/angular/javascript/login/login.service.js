(function(){
	"use strict";
	
	angular.module('beatMyFindApp')
	.service('LoginService',LoginService)
	.config(function($httpProvider){
		$httpProvider.defaults.xsrfCookieName = 'csrfToken';
		$httpProvider.defaults.xsrfHeaderName = 'X-CSRFToken';
	});
	
	/*.config(function($httpProvider){
		$httpProvider.defaults.xsrfCookieName = 'csrfToken';
		$httpProvider.defaults.xsrfHeaderName = 'X-CSRFToken';
	})*/
	
	/*.config(function($httpProvider){
		$httpProvider.defaults.transformRequest = function(data){
				if(data==undefined){
					return data;
				}
				return $param(data);
			}
		$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
	})*/
	
	LoginService.$inject = ['$http','basePath'];
	function LoginService($http,basePath){
		var loginService = this;
		
		loginService.validateUser = function(user){
			console.log("user in service: "+angular.toJson(user));
			return $http({
				method:'POST',
				url: (basePath+'/authenticate'),
				data: user
			})
			.then(function(response){
				console.log("response in login service: "+response.data);
				return response;
			});
		}
	}
})();