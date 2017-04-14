package com.shrishti.siri.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;

import com.shrishti.siri.presentation.model.DealsSearchModel;
import com.shrishti.siri.service.util.AmazonDealsUtil;

@Service
public class DealsService {
	
	/*
     * Your AWS Access Key ID, as taken from the AWS Your Account page.
     */
    private static final String AWS_ACCESS_KEY_ID = "AKIAIH7UNTQ4XMG7J2PQ";

    /*
     * Your AWS Secret Key corresponding to the above ID, as taken from the AWS
     * Your Account page.
     */
    private static final String AWS_SECRET_KEY = "Fj8E9OtCpwkQWCVLzRA6pANuhdk5SI5I4lMV/pZO";

    /*
     * Use one of the following end-points, according to the region you are
     * interested in:
     * 
     *      US: ecs.amazonaws.com 
     *      CA: ecs.amazonaws.ca 
     *      UK: ecs.amazonaws.co.uk 
     *      DE: ecs.amazonaws.de 
     *      FR: ecs.amazonaws.fr 
     *      JP: ecs.amazonaws.jp
     * 
     */
    private static final String ENDPOINT = "webservices.amazon.com";

    /*
     * The Item ID to lookup. The value below was selected for the US locale.
     * You can choose a different value if this value does not work in the
     * locale of your choice.
     */
    public String fetchDeals(DealsSearchModel dealsSearchModel) {
        String output = "";
    	
    	/*
         * Set up the signed requests helper 
         */
        AmazonDealsUtil helper;
        try {
            helper = AmazonDealsUtil.getInstance(ENDPOINT, AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        } catch (Exception e) {
            e.printStackTrace();
            return output;
        }
        
        String requestUrl = null;

        /* The helper can sign requests in two forms - map form and string form */
        
        /*
         * Here is an example in map form, where the request parameters are stored in a map.
         */
        System.out.println("Map form example:");
       
        Map<String, String> params = new HashMap<String, String>();
        params.put("Service", "AWSECommerceService");
        params.put("AssociateTag", "buynowspeci05-20");
        params.put("Operation", "ItemSearch");
        params.put("Condition", "All");
        params.put("Availability", "Available");
        params.put("ResponseGroup", "Offers,Images,PromotionSummary,ItemAttributes,Reviews");
        params.put("Version", "2013-08-01");
        
        //To be collected from user
        params.put("SearchIndex", dealsSearchModel.getSearchIndex());
        params.put("Keywords", dealsSearchModel.getSearchString());
        
        requestUrl = helper.sign(params);
        System.out.println("Signed Request is \"" + requestUrl + "\"");

        try {
        	output = fetchTitle(requestUrl);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        int indexOfQMark = output.indexOf('?', 1);
		
		String xmlToBeConverted = output.substring(indexOfQMark+2);
		
		System.out.println("xmlToBeConverted: "+xmlToBeConverted);
		
		JSONObject jsonString = XML.toJSONObject(xmlToBeConverted);
		System.out.println("json1:: "+jsonString.toString());
		
        return jsonString.toString();
    }

    private static String fetchTitle(String requestUrl) throws IOException{
    	String retVal = "";
    	HttpURLConnection huc = null;
    	try {
			huc=(HttpURLConnection)new URL(requestUrl).openConnection();
			huc.setRequestProperty("Content-Type", "application/json");
			huc.setRequestProperty("Accept", "application/json");
			InputStream i=huc.getInputStream();
			while (i.read() >= 0) {
				retVal = new BufferedReader(new InputStreamReader(huc.getInputStream()))
	  			  .lines().collect(Collectors.joining("\n"));
			}
	    	
	    	System.out.println("RetVal: "+retVal);
		} catch (MalformedURLException e) {
			System.out.println(">>>>>>>>>>>>>>1");
			e.printStackTrace();
			String result = new BufferedReader(new InputStreamReader(huc.getErrorStream()))
	    			  .lines().collect(Collectors.joining("\n"));
	    	System.out.println("ErrorStream: "+result);
		} catch (IOException e) {
			System.out.println(">>>>>>>>>>>>>>2");
			e.printStackTrace();
			String result = new BufferedReader(new InputStreamReader(huc.getErrorStream()))
	    			  .lines().collect(Collectors.joining("\n"));
	    	System.out.println("ErrorStream: "+result);
		}
    	
    	return retVal;
    }

}
