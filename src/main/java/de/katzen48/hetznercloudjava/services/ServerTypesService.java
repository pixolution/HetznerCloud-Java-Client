package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.server.GetServersResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ServerTypesService 
{
	@Headers({ "Accept: application/json" })
	@GET("server_types")
	public Call<GetServersResponse> getServerTypes();
	
	@Headers({ "Accept: application/json" })
	@GET("server_types?name={name}")
	public Call<GetServersResponse> getServerTypeByName(@Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("server_types/{id}")
	public Call<GetServersResponse> getServerType(@Path("id") int id);
}
