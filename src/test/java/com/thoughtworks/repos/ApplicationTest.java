package com.thoughtworks.repos;

import org.springframework.web.client.RestTemplate;

import com.thoughtworks.repos.model.ThoughtworksRepositories;

public class ApplicationTest {
	
	public static final String REST_SERVICE_URI = "http://localhost:3000/thoughtworks";
	
	/* GET */
    private static void listAllLanguages() {
        System.out.println("Testing listAllLanguages API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        ThoughtworksRepositories response = restTemplate.getForObject(REST_SERVICE_URI+"/repositories/", ThoughtworksRepositories.class);
        
        if (response != null) {
        	System.out.println(response.toString());
        } else {
            System.out.println("No language returned----------");
        }
    }
    
    public static void main(String args[]){
    	listAllLanguages();
    }
}
