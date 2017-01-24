(function(){
	"use strict";
	
	angular.module('beatMyFindApp')
	.component('loading', {
		template: '<img src="resources/images/spinner.svg" ng-if="$ctrl.show">',
		controller: LoadingController
	});
	
	LoadingController.$inject = ['$rootScope']
	function LoadingController($rootScope){
		var $ctrl = this;
		var listner;
		
		$ctrl.$onInit = function(){
			$ctrl.show = false;
			listner = $rootScope.$on('spinner:activate', onSpinnerActivate);
		}
		
		$ctrl.$onDestroy = function() {
			listner();
		}
		
		function onSpinnerActivate(evernt, data){
			$ctrl.show = data.on;
		}
	}
})();