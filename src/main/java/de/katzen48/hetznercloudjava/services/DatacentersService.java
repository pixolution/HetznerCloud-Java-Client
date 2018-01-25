package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.datacenters.GetDatacenterResponse;
import de.katzen48.hetznercloudjava.reponses.datacenters.GetDatacentersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface DatacentersService 
{
	@Headers({ "Accept: application/json" })
	@GET("datacenters")
	public Call<GetDatacentersResponse> getDatacenters();
	
	@Headers({ "Accept: application/json" })
	@GET("datacenters?name={name}")
	public Call<GetDatacentersResponse> getDatacentersByName(@Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("datacenters/{id}")
	public Call<GetDatacenterResponse> getDatacenter(@Path("id") int id);
}
