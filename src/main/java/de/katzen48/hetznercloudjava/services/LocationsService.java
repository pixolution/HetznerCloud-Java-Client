package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.locations.GetLocationResponse;
import de.katzen48.hetznercloudjava.reponses.locations.GetLocationsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface LocationsService 
{
	@Headers({ "Accept: application/json" })
	@GET("locations")
	public Call<GetLocationsResponse> getLocations();
	
	@Headers({ "Accept: application/json" })
	@GET("locations?name={name}")
	public Call<GetLocationsResponse> getLocationsByName(@Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("locations/{id}")
	public Call<GetLocationResponse> getLocation(@Path("id") int id);
}
