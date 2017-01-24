(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.controller("PostQueryController", PostQueryController);

	PostQueryController.$inject = ['PostQueryService','CommonService']
	function PostQueryController(PostQueryService, CommonService){
		var postQueryCtrl = this;
		postQueryCtrl.UserQuery={};
		postQueryCtrl.UserQuery.user = {};
		
		postQueryCtrl.postQuery = function(){
			console.log("inside postQuery controller");
			
			postQueryCtrl.UserQuery.user.userId = CommonService.getUserId();
			
			console.log("user id in controller1: "+postQueryCtrl.UserQuery.user.userId+" 2: "+CommonService.getUserId());
			
			var promise = PostQueryService.postQuery(postQueryCtrl.UserQuery);
			
			promise.then(function(response){
				postQueryCtrl.successMessage = "Query posted successfully.";
				postQueryCtrl.UserQuery.queryText="";
			}).catch(function(error){
				console.log("exception while posting query");
				postQueryCtrl.errorMessage = "Posting query failed. Please try again.";
			});
			
		};
	}
})();