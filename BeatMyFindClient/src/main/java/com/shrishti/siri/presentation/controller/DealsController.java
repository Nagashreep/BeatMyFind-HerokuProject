package com.shrishti.siri.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.shrishti.siri.presentation.model.DealsSearchModel;
import com.shrishti.siri.service.DealsService;

@RestController
public class DealsController {
	
	@Autowired
	DealsService dealsService;
	
	
	@RequestMapping(value="/fetchDeals", method=RequestMethod.POST)
	public String fetchDeals(@RequestBody DealsSearchModel dealsSearchModel){
		System.out.println("searchIndex in Deals controller: "+dealsSearchModel.getSearchIndex()+" searchString: "+dealsSearchModel.getSearchString());
		
		return dealsService.fetchDeals(dealsSearchModel);
		
	}
	
	
    
    

}
