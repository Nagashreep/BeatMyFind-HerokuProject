(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.controller('NewUserRegistrationController',NewUserRegistrationController);
	
	NewUserRegistrationController.$inject = ['NewUserRegistrationService','CommonService','$rootScope']
	function NewUserRegistrationController(NewUserRegistrationService,CommonService,$rootScope){
		var newUserCtrl = this;
		newUserCtrl.newUser = {};
	
		newUserCtrl.checkIfPasswordsSame = function(){
			if(newUserCtrl.newUser.userPassword!="" || newUserCtrl.newUser.confirmUserPassword!=""){
				console.log("one of the passwords is not emty");
				if(newUserCtrl.newUser.userPassword!=newUserCtrl.newUser.confirmUserPassword){
					console.log("both passwords are not same: 1: "+newUserCtrl.newUser.userPassword+" 2: "+newUserCtrl.newUser.confirmUserPassword);
					newUserCtrl.passwordMismatchError ="Passwords do not match. Please correct it";
				}else{
					console.log("both passwords are same: 1: "+newUserCtrl.newUser.userPassword+" 2: "+newUserCtrl.newUser.confirmUserPassword);
					newUserCtrl.passwordMismatchError ="";
				}
			}else{
				console.log("both passwords are blank");
				newUserCtrl.passwordMismatchError ="";
			}
		};
		
		newUserCtrl.checkIfUserNameUnique = function(){
			if(newUserCtrl.userName!=''){
				var promise = NewUserRegistrationService.isUserNameUnique(newUserCtrl.newUser.userName);
				promise.then(function(response){
					console.log("response in controller: "+response.data)
					if(response.data==true){
						newUserCtrl.uniqueUserNameError = "";
						console.log("user name is unique");
					}else{
						newUserCtrl.uniqueUserNameError = "This user name is already taken. Please try a different one";
					}
				})
			}else{
				newUserCtrl.uniqueUserNameError = "";
			}
		};
		
		newUserCtrl.registerUser = function(){
			var promise = NewUserRegistrationService.registerUser(newUserCtrl.newUser);
			promise.then(function(response){
				if(response.data!="" && response.data.userId!=""){
					CommonService.saveUser(response.data);
					newUserCtrl.newUser = {};
					console.log("user saved successfully");
					newUserCtrl.successMessage = "User successfully registered.";
					$rootScope.$broadcast("SuccessfulLoginEvent");
				}else{
					newUserCtrl.errorMessage = "Error registering the user. Please try again."
					console.log("save functionality failed");
				}
			})
		}
		
		
	}
	
})();