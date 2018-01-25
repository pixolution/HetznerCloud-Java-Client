package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.datacenters.locations.GetDatacenterLocationResponse;
import de.katzen48.hetznercloudjava.reponses.datacenters.locations.GetDatacenterLocationsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DatacenterLocationsService 
{
	@Headers({ "Accept: application/json" })
	@GET("locations")
	public Call<GetDatacenterLocationsResponse> getLocations();
	
	@Headers({ "Accept: application/json" })
	@GET("locations?name={name}")
	public Call<GetDatacenterLocationsResponse> getLocationsByName(@Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("locations/{id}")
	public Call<GetDatacenterLocationResponse> getLocation(@Path("id") int id);
}
