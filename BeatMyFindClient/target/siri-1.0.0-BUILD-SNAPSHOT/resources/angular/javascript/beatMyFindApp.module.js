(function(){
	"use strict";
	
	angular.module('beatMyFindApp',['ui.router','ngCookies','checklist-model','ngSanitize'])
	.constant('basePath', '/BeatMyFind')
	.config(config);
	
	config.$inject = ['$httpProvider']
	function config($httpProvider){
		$httpProvider.interceptors.push('loadingHttpInterceptor');
	}
})();