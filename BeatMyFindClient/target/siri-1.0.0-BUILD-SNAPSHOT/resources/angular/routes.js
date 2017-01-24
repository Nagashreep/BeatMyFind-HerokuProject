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
		.state('discussionForumHome',{
			templateUrl:'resources/angular/template/discussionForum/forumTemplate.html',
			abstract: true
		})
		.state('discussionForumHome.openDiscussionForumSummary',{
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
		.state('discussionForumHome.closedDiscussionForumSummary',{
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
		.state('discussionForumHome.expiredDiscussionForumSummary',{
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
		.state('discussionForumHome.discussionForumDetail',{
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
		.state('discussionForumHome.postQuery',{
			url: '/postQuery',
			templateUrl : 'resources/angular/template/discussionForum/postQuery.html',
			controller: 'PostQueryController',
			controllerAs: 'postQueryCtrl'
		})
		.state('register',{
			url: '/register',
			templateUrl : 'resources/angular/template/login/newUserRegistration.html',
			controller: 'NewUserRegistrationController',
			controllerAs: 'newUserCtrl'
		})
		.state('userAccountHome',{
			url: '/userAccount/home',
			templateUrl : 'resources/angular/template/userAccount/userAccountHome.html'
		})
		.state('userAccountTemplate',{
			templateUrl:'resources/angular/template/userAccount/userAccountTemplate.html',
			abstract: true
		})
		
		//To be fixed
		.state('userAccountTemplate.userAccountCloseQueries',{
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
		.state('userAccountTemplate.userAccountFavQueries',{
			url: '/userAccount/favQueries',
			templateUrl : 'resources/angular/template/userAccount/favQueries.html',
			controller: 'userAccountController',
			controllerAs: 'userAccountCtrl',
			resolve: {
				myQueryList: ['userAccountService', function(userAccountService){
					return userAccountService.fetchMyFavQueries();
				}]	
			}
		})
		.state('userAccountTemplate.userAccountEditProfile',{
			url: '/userAccount/editProfile',
			templateUrl : 'resources/angular/template/userAccount/editProfile.html'
		});
	}
})();