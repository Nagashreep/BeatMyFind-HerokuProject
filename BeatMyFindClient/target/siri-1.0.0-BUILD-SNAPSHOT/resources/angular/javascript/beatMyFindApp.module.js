(function(){
	"use strict";
	
	angular.module('beatMyFindApp',['ui.router','ngCookies','checklist-model','ngSanitize'])
	.constant('basePath', '../')
	.config(config);
	
	config.$inject = ['$httpProvider']
	function config($httpProvider){
		$httpProvider.interceptors.push('loadingHttpInterceptor');
	}
})();