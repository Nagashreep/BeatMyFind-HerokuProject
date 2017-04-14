(function(){
	"use strict";
	
	angular.module('beatMyFindApp')
	.config(routerConfig);
	
	routerConfig.$inject = ['$stateProvider']
	function routerConfig($stateProvider){
		$stateProvider
		.state('home',{
			url:'/',
			templateUrl:'resources/angular/template/home/homePageDefaultContent.html'
		})
		.state('appTemplateBeforeLogging',{
			templateUrl:'resources/angular/template/appTemplate/appTemplateBeforeLogging.html',
			abstract: true
		})
		.state('appTemplateAfterLogging',{
			templateUrl:'resources/angular/template/appTemplate/appTemplateAfterLogging.html',
			abstract: true
		})
		.state('appTemplateBeforeLogging.logout',{
			url: '/logout',
			template: "Thank you for using the application. If you wish to login again, please click on this link <a href='/BeatMyFind'>Login</a>",
			controller: function(CommonService) {
				CommonService.removeUser();
			}
		})
		.state('appTemplateAfterLogging.openDiscussionForumSummary',{
			url: '/openDiscussionForum',
			templateUrl: 'resources/angular/template/discussionForum/forumSummary.html',
			controller: 'UserQueryController',
			controllerAs: 'userQueryCtrl',
			resolve: {
				userQueries: ['UserQueryService', function(UserQueryService){
					return UserQueryService.fetchOpenUserQueries();
				}]
			}
		})
		.state('appTemplateAfterLogging.closedDiscussionForumSummary',{
			url: '/closedDiscussionForum',
			templateUrl: 'resources/angular/template/discussionForum/forumSummary.html',
			controller: 'UserQueryController',
			controllerAs: 'userQueryCtrl',
			resolve: {
				userQueries: ['UserQueryService', function(UserQueryService){
					return UserQueryService.fetchClosedUserQueries();
				}]
			}
		})
		.state('appTemplateAfterLogging.expiredDiscussionForumSummary',{
			url: '/expiredDiscussionForum',
			templateUrl: 'resources/angular/template/discussionForum/forumSummary.html',
			controller: 'UserQueryController',
			controllerAs: 'userQueryCtrl',
			resolve: {
				userQueries: ['UserQueryService', function(UserQueryService){
					return UserQueryService.fetchExpiredUserQueries();
				}]
			}
		})
		.state('appTemplateAfterLogging.discussionForumDetail',{
			url : '/detail/{queryId}',
			templateUrl : 'resources/angular/template/discussionForum/forumDetail.html',
			controller: 'UserQueryDetailController',
			controllerAs: 'userQueryDetailCtrl',
			resolve: {
				userQuery: ['UserQueryService','$stateParams', function(UserQueryService,$stateParams){
					return UserQueryService.fetchQueryDetails($stateParams.queryId);
				}]
			}
		})
		.state('appTemplateAfterLogging.postQuery',{
			url: '/postQuery',
			templateUrl : 'resources/angular/template/discussionForum/postQuery.html',
			controller: 'PostQueryController',
			controllerAs: 'postQueryCtrl'
		})
		.state('appTemplateBeforeLogging.register',{
			url: '/register',
			templateUrl : 'resources/angular/template/login/newUserRegistration.html',
			controller: 'NewUserRegistrationController',
			controllerAs: 'newUserCtrl'
		})
		.state('appTemplateAfterLogging.userAccountHome',{
			url: '/userAccount/home',
			templateUrl : 'resources/angular/template/userAccount/userAccountHome.html',
			controller: 'UserAccountHomeController',
			controllerAs: 'userAccountHomeCtrl'
		})
		.state('appTemplateAfterLogging.userAccountTemplate',{
			templateUrl:'resources/angular/template/userAccount/userAccountTemplate.html',
			abstract: true
		})
		//Reason for closing to be added
		.state('appTemplateAfterLogging.userAccountTemplate.userAccountCloseQueries',{
			url: '/userAccount/closeQueries',
			templateUrl : 'resources/angular/template/userAccount/closeQueries.html',
			controller: 'UserAccountController',
			controllerAs: 'userAccountCtrl',
			resolve: {
				userQueryList: ['UserAccountService', function(UserAccountService){
					return UserAccountService.fetchUserQueries();
				}]	
			}
		})
		.state('appTemplateAfterLogging.dealsHome',{
			url: '/deals/home',
			templateUrl: 'resources/angular/template/deals/dealsHome.html',
			controller: 'DealsController',
			controllerAs: 'dealsCtrl',
			resolve: {
				editorial: ['EditorialService', function(EditorialService){
					return EditorialService.retrieve();
				}]	
			}
		})
		//To be fixed
		.state('appTemplateAfterLogging.userAccountTemplate.userAccountFavQueries',{
			url: '/userAccount/favQueries',
			templateUrl : 'resources/angular/template/userAccount/favQueries.html'
			/*controller: 'userAccountController',
			controllerAs: 'userAccountCtrl',
			resolve: {
				myQueryList: ['userAccountService', function(userAccountService){
					return userAccountService.fetchMyFavQueries();
				}]	
			}*/
		})
		.state('appTemplateAfterLogging.userAccountTemplate.userAccountEditProfile',{
			url: '/userAccount/editProfile',
			templateUrl : 'resources/angular/template/userAccount/editProfile.html'
		})
		.state('appTemplateAfterLogging.userAccountTemplate.editorialContent',{
			url:'/userAccount/editorial',
			templateUrl: 'resources/angular/template/userAccount/editorial.html',
			controller: 'EditorialController',
			controllerAs: 'editorialCtrl',
			resolve: {
				editorial: ['EditorialService', function(EditorialService){
					return EditorialService.retrieve();
				}]	
			}
		});
	}
})();