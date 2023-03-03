package com.ipl.xpto.trackingVehicles.business;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Value;

import com.ipl.xpto.trackingVehicles.util.ApiConsumer;

import java.util.UUID;

public class CustomerBusiness {
	
	@Value("${business.baseUrl.customers}")
	private String _baseUrl;
	
	public CustomerBusiness() {
		_baseUrl = "http://localhost:8081/tracking";//org.hibernate.cfg.Environment.getProperties().getProperty("business.baseUrl.customers");
	}

	public boolean VerifyExistingCustomer(UUID id) {
		boolean result = false;
        ApiConsumer apiConsumer = new ApiConsumer(_baseUrl);
        try {
        	CompletableFuture<HttpResponse<String>> responseFuture = apiConsumer.get("/customers/" + id);
        	HttpResponse<String> response;
			response = responseFuture.get();
			System.out.print(response.statusCode());
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