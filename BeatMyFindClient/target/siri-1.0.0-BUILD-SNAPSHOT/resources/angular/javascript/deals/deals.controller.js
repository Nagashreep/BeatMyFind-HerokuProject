(function(){
	
	"use strict";
	
	angular.module('beatMyFindApp')
	.controller('DealsController', DealsController);
	
	DealsController.$inject = ['DealsService','editorial']
	function DealsController(DealsService,editorial){
		var dealsCtrl = this;
		
		console.log("editorial: "+editorial);
		if(editorial!=null && editorial!=undefined){
			dealsCtrl.editorial = editorial;
			
			if(dealsCtrl.editorial.mainContent!=undefined){
				dealsCtrl.editorial.mainContentFormatted = (dealsCtrl.editorial.mainContent).replace(/\n/g, '<br/>');
			}
		}else{
			dealsCtrl.editorial = {};
		}
		
		dealsCtrl.items = {};
		
		dealsCtrl.dealsSearchModel = {};
		
		dealsCtrl.showDeals = false;
		
		dealsCtrl.searchIndexList = ["Appliances", "ArtsAndCrafts", "Automotive", "Baby", "Beauty", "Blended", "Books", "Collectibles", "Electronics", "Fashion", "FashionBaby",
				"FashionBoys", "FashionGirls", "FashionMen", "FashionWomen", "GiftCards", "Grocery", "Handmade", "HealthPersonalCare", "HomeGarden", "Industrial", "KindleStore",
				"LawnAndGarden", "Luggage", "Magazines", "Marketplace", "Merchants", "MobileApps", "Movies", "MP3Downloads", "Music", "MusicalInstruments", "OfficeProducts", 
				"Pantry", "PCHardware", "PetSupplies", "Software", "SportingGoods", "Tools", "Toys", "UnboxVideo", "Vehicles", "VideoGames", "Wine", "Wireless"];
		
		dealsCtrl.find = function(){
			console.log("inside deals controller");
			
			var promise = DealsService.find(dealsCtrl.dealsSearchModel);
			
			promise.then(function (response){
				dealsCtrl.items = response.data.ItemSearchResponse.Items;
				console.log("1>> "+response.data);
				console.log("Items>>: "+dealsCtrl.items)
				dealsCtrl.showDeals = true;
			}).catch(function(exception){
				console.log("exception while fetching amazon offers");
			})
			
			
		}
	}
	
})();