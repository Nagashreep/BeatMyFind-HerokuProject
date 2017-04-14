(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.controller('EditorialController',EditorialController);
	
	EditorialController.$inject = ['EditorialService','editorial','CommonService','$state']
	function EditorialController(EditorialService,editorial,CommonService,$state){
		var editorialCtrl = this;
		editorialCtrl.showEditorialPreview = false;
		console.log("editorial: "+editorial);
		if(editorial!=null && editorial!=undefined){
			editorialCtrl.editorial = editorial;
		}else{
			editorialCtrl.editorial = {};
		}
		
		editorialCtrl.preview = function(){
			console.log("preview function called>>");
			editorialCtrl.editorial.mainContentFormatted = (editorialCtrl.editorial.mainContent).replace(/\n/g, '<br/>');
			editorialCtrl.showEditorialPreview = true;
		}
		
		editorialCtrl.back = function(){
			console.log("back function called");
			editorialCtrl.showEditorialPreview = false;
		}
		
		editorialCtrl.publish = function(){
			console.log("publish function called");
			
			editorialCtrl.editorial.user={};
			editorialCtrl.editorial.user.userId = CommonService.getUserId();
			console.log("user id in controller1: "+editorialCtrl.editorial.user.userId+" 2: "+CommonService.getUserId());
			
			var promise = EditorialService.publish(editorialCtrl.editorial);
			promise.then(function(response){
				$state.go('appTemplateAfterLogging.dealsHome');
			}).catch(function(error){
	    		console.log("error during publishing editorial");
	    	});
		}
	}
})();