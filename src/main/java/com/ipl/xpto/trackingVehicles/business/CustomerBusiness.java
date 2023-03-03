package com.ipl.xpto.trackingVehicles.business;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ipl.xpto.trackingVehicles.util.ApiConsumer;

import java.util.UUID;

@Service
public class CustomerBusiness {
	
	private final String baseUrl;
	
	public CustomerBusiness(@Value("${customers.base.url}") String baseUrl) {
		this.baseUrl = baseUrl;
		//baseUrl = "http://localhost:8081/tracking";//org.hibernate.cfg.Environment.getProperties().getProperty("business.baseUrl.customers");
	}

	public boolean VerifyExistingCustomer(UUID id) {
		boolean result = false;
        ApiConsumer apiConsumer = new ApiConsumer(baseUrl);
        try {
        	CompletableFuture<HttpResponse<String>> responseFuture = apiConsumer.get("/customers/" + id);
        	HttpResponse<String> response;
			response = responseFuture.get();
			System.out.println(response.statusCode());
			if (response.statusCode() == 200) {				
				result = true;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.print(result);
        return result;
	}
}
