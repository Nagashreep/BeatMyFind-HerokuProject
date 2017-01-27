(function(){
	"use strict";
	
	angular.module('beatMyFindApp',['ui.router','ngCookies','checklist-model'])
	.constant('basePath', 'http://localhost:8080/BeatMyFind')
	.config(config);
	
	config.$inject = ['$httpProvider']
	function config($httpProvider){
		$httpProvider.interceptors.push('loadingHttpInterceptor');
	}
})();