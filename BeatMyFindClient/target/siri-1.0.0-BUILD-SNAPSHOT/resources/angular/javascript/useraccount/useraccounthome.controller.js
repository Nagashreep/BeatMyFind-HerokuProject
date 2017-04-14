(function(){
	
	'use strict';
	
	angular.module('beatMyFindApp')
	.controller('UserAccountHomeController',UserAccountHomeController);
			
	UserAccountHomeController.$inject=["CommonService"]
	function UserAccountHomeController(CommonService){
		
		var userAccountHomeCtrl = this;
		
		userAccountHomeCtrl.user = CommonService.getUser();
		
		if(userAccountHomeCtrl.user.isAdmin=='Y'){
			userAccountHomeCtrl.showEditorialLink = true;
		}else{
			userAccountHomeCtrl.showEditorialLink = false;
		}
	}
	
})();