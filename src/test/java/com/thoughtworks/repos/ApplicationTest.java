package com.thoughtworks.repos;

import org.springframework.web.client.RestTemplate;

import com.thoughtworks.repos.model.TWGitHubResponse;

public class ApplicationTest {
	
	public static final String REST_SERVICE_URI = "http://localhost:3000/thoughtworks";
	
	/* GET */
    private static void listAllLanguages() {
        System.out.println("Testing listAllLanguages API-----------");
         
        RestTemplate restTemplate = new RestTemplate();
        TWGitHubResponse response = restTemplate.getForObject(REST_SERVICE_URI+"/repositories/", TWGitHubResponse.class);
        
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
