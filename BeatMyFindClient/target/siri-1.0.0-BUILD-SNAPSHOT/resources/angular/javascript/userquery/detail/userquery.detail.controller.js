(function(){
	"use strict";
	
	angular.module('beatMyFindApp')
	.controller('UserQueryDetailController',UserQueryDetailController);
	
	UserQueryDetailController.$inject = ['userQuery','CommonService','UserQueryDetailService'];
	function UserQueryDetailController(userQuery,CommonService,UserQueryDetailService){
		console.log("inside controller: "+userQuery.queryText);
		var userQueryDetailCtrl = this;
		userQueryDetailCtrl.query = userQuery;
		userQueryDetailCtrl.UserComment = {};
		console.log("before setting user");
		userQueryDetailCtrl.UserComment.user = {};
		userQueryDetailCtrl.UserComment.userQuery = {};
		console.log("user id inside service:? "+userQueryDetailCtrl.UserComment.user.userId+" 2: "+CommonService.getUserId());
		userQueryDetailCtrl.UserComment.userQuery.queryId = userQueryDetailCtrl.query.queryId;
		
		userQueryDetailCtrl.postComment = function(){
			
			console.log(">>> inside post comment: NewCommentText: "+userQueryDetailCtrl.UserComment.commentText+" QueryId: "+userQueryDetailCtrl.UserComment.userQuery.queryId+" UserName: "+userQueryDetailCtrl.UserComment.user.userId);
			var promise = UserQueryDetailService.addComment(userQueryDetailCtrl.UserComment);
			 
			 promise.then(function(response){
				 userQueryDetailCtrl.query = response.data;
				 userQueryDetailCtrl.UserComment.commentText = "";
				 userQueryDetailCtrl.successMessage = "Comment posted successfully."
			 }).catch(function (error){
				 //Display error message on screen
				 userQueryDetailCtrl.errorMessage = "Error posting comment. Please try again."
			 });
			
		}
		
		
		userQueryDetailCtrl.isToBeDisabled = function(isFormValid){
			userQueryDetailCtrl.UserComment.user.userId = CommonService.getUserId();
			if(isFormValid && (userQueryDetailCtrl.UserComment.user.userId!=undefined && userQueryDetailCtrl.UserComment.user.userId!='')){
				return false;
			}else{
				userQueryDetailCtrl.titleText = "Please ensure that you have logged in before you post a comment";
				return true;
			}
		}
	}
})();