(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.controller('UserAccountController',UserAccountController);
	
	UserAccountController.$inject = ['userQueryList','UserAccountService', 'CommonService']
	function UserAccountController(userQueryList,UserAccountService,CommonService){
		var userAccountCtrl = this;
		
		userAccountCtrl.userQueries = userQueryList;
		
		userAccountCtrl.queryIdsToClose = [];
		
		userAccountCtrl.user = CommonService.getUser();
		
		if(userAccountCtrl.user.isAdmin=='Y'){
			userAccountCtrl.showEditorialLink = true;
		}else{
			userAccountCtrl.showEditorialLink = false;
		}
		
		console.log("showEditorialLink: "+userAccountCtrl.showEditorialLink);
		
		userAccountCtrl.closeQueries = function(){
			var promise = UserAccountService.closeQueries(userAccountCtrl.queryIdsToClose);
			promise.then(function(response){
				userAccountCtrl.userQueries = response.data;
				console.log("data in controller: "+userAccountCtrl.userQueries);
				userAccountCtrl.queryIdsToClose = [];
				userAccountCtrl.successMessage = 'Query/Queries closed successfully';
			}).catch(function(error){
				userAccountCtrl.errorMessage = 'Closing query failed. Please try again';
			});
		}
		
		userAccountCtrl.isButtonToBeDisabled = function(){
			if(userAccountCtrl.queryIdsToClose.length==0){
				console.log("inside isButtonToBeDisabled, value is true");
				return true;
			}else{
				console.log("inside isButtonToBeDisabled, value is false");
				return false;
			}
		}
		
	}
	
})();