(function(){
	"use strict";
	
	angular.module('beatMyFindApp')
	.controller('LoginController', LoginController);
	
	LoginController.$inject = ['LoginService','CommonService','$scope','$state'];
	function LoginController(LoginService,CommonService,$scope,$state){
		var loginCtrl = this;
		loginCtrl.userValidationError = false;
		loginCtrl.userAuthenticated = CommonService.isUserAuthenticated();
		console.log("userAuth value in login controller: "+loginCtrl.userAuthenticated);
		if(loginCtrl.userAuthenticated){
			loginCtrl.userName = CommonService.getUserName();
		}
		loginCtrl.displayForm = false;
		loginCtrl.toggle = function() {
			loginCtrl.displayForm = !loginCtrl.displayForm;
			
	    };
	    loginCtrl.validateUser = function(){
	    	console.log("user object in validate function of controller: "+loginCtrl.user);
	    	LoginService.validateUser(loginCtrl.user)
	    	.then(function(response){
	    		console.log("response in login controller: "+response.data.userName);
	    		if(response.data.userName==undefined){
	    			console.log("Invalid credentials");
		    		loginCtrl.userValidationError = true;
	    		}else{
		    		loginCtrl.displayForm = false;
		    		loginCtrl.userAuthenticated = true;
		    		loginCtrl.userName = response.data.userName;
		    		CommonService.saveUser(response.data);
		    		console.log("switching state before");
		    		$state.go('appTemplateAfterLogging.dealsHome');
	    		}
	    	}).catch(function(exception){
	    		console.log("error during login");
	    		loginCtrl.userValidationError = true;
	    	});
	    };
	    
	    $scope.$on("SuccessfulLoginEvent", function(event){
	    	loginCtrl.userAuthenticated = true;
	    	loginCtrl.userName = CommonService.getUserName();
	    	loginCtrl.displayForm = false;
	    	console.log("received the event in login controller");
	    })
	    
	}
	
})();