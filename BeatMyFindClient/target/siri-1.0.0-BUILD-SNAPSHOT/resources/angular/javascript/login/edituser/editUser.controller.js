(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.controller('NewUserRegistrationController',NewUserRegistrationController);
	
	NewUserRegistrationController.$inject = ['NewUserRegistrationService','CommonService','$rootScope']
	function NewUserRegistrationController(NewUserRegistrationService,CommonService,$rootScope){
		var editUserCtrl = this;
		editUserCtrl.newUser = CommonService.getUser();
	
		editUserCtrl.checkIfPasswordsSame = function(){
			if(editUserCtrl.newUser.userPassword!="" || editUserCtrl.newUser.confirmUserPassword!=""){
				console.log("one of the passwords is not emty");
				if(editUserCtrl.newUser.userPassword!=editUserCtrl.newUser.confirmUserPassword){
					console.log("both passwords are not same: 1: "+editUserCtrl.newUser.userPassword+" 2: "+editUserCtrl.newUser.confirmUserPassword);
					editUserCtrl.passwordMismatchError ="Passwords do not match. Please correct it";
				}else{
					console.log("both passwords are same: 1: "+editUserCtrl.newUser.userPassword+" 2: "+editUserCtrl.newUser.confirmUserPassword);
					editUserCtrl.passwordMismatchError ="";
				}
			}else{
				console.log("both passwords are blank");
				editUserCtrl.passwordMismatchError ="";
			}
		};
		
		editUserCtrl.checkIfUserNameUnique = function(){
			if(editUserCtrl.userName!=''){
				var promise = NewUserRegistrationService.isUserNameUnique(editUserCtrl.newUser.userName);
				promise.then(function(response){
					console.log("response in controller: "+response.data)
					if(response.data==true){
						editUserCtrl.uniqueUserNameError = "";
						console.log("user name is unique");
					}else{
						editUserCtrl.uniqueUserNameError = "This user name is already taken. Please try a different one";
					}
				})
			}else{
				editUserCtrl.uniqueUserNameError = "";
			}
		};
		
		editUserCtrl.registerUser = function(){
			var promise = NewUserRegistrationService.registerUser(editUserCtrl.newUser);
			promise.then(function(response){
				if(response.data!="" && response.data.userId!=""){
					CommonService.saveUser(response.data);
					editUserCtrl.newUser = {};
					console.log("user saved successfully");
					editUserCtrl.successMessage = "User successfully registered.";
					$rootScope.$broadcast("SuccessfulLoginEvent");
				}else{
					editUserCtrl.errorMessage = "Error registering the user. Please try again."
					console.log("save functionality failed");
				}
			})
		}
		
		
	}
	
})();