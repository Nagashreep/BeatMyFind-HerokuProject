(function(){
	"use strict";
	
	angular.module('beatMyFindApp')
	.directive('login',LoginDirective);
	
	
	function LoginDirective(){
		var ddo = {
			templateUrl: 'resources/angular/template/login/loginHome.html',
		};
		return ddo;
	}
})();