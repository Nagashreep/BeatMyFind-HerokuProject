(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.controller('UserQueryController', UserQueryController);
	
	UserQueryController.$inject = ['userQueries','CommonService'];
	function UserQueryController(userQueries,CommonService){
		var userQueryCtrl = this;
		userQueryCtrl.userQueries = userQueries;
		
		console.log("userQueryCtrl.userQueries: "+userQueryCtrl.userQueries);
		
		userQueryCtrl.isButtonToBeDisabled = function(){
			if(CommonService.isUserAuthenticated()){
				return false;
			}else{
				return true;
			}
		};
	}
})();