(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.service('CommonService', CommonService);
	
	CommonService.$inject = ["$cookieStore"]
	function CommonService($cookieStore){
		
		var commonService = this;
		
		commonService.saveUser = function(user){
			console.log("user Id getting saved in common Service: "+user.userId);
			$cookieStore.put("user", user);
			console.log("fetching value in common service after setting in session: "+$cookieStore.get("user").userId);
		}
		commonService.getUserId = function(){
			if($cookieStore.get("user")==undefined || ($cookieStore.get("user").userId==undefined || $cookieStore.get("user").userId=='')){
				return "";
			}else{
				console.log("user id in common service: "+$cookieStore.get("user").userId);
				return $cookieStore.get("user").userId;
			}
		}
		commonService.removeUser = function(){
			$cookieStore.remove("user");
		}
		commonService.getUserName = function(){
			console.log("user name in common service: "+$cookieStore.get("user").userName);
			if($cookieStore.get("user")==undefined || $cookieStore.get("user").userName==undefined){
				return "";
			}else{
				return $cookieStore.get("user").userName;
			}
		}
		commonService.isUserAuthenticated= function(){
			console.log("user id in common service isUserAuth1: "+commonService.userId);
			if($cookieStore.get("user")==undefined || ($cookieStore.get("user").userId==undefined || $cookieStore.get("user").userId=='')){
				return false;
			}else{
				return true;
			}
		}
		
	}
})();