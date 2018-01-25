package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.pricing.GetPricingResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface PricingService 
{
	@Headers({ "Accept: application/json" })
	@GET("pricing")
	public Call<GetPricingResponse> getPricing();
}
