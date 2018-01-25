package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.servertypes.GetServerTypeResponse;
import de.katzen48.hetznercloudjava.reponses.servertypes.GetServerTypesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ServerTypesService 
{
	@Headers({ "Accept: application/json" })
	@GET("server_types")
	public Call<GetServerTypesResponse> getServerTypes();
	
	@Headers({ "Accept: application/json" })
	@GET("server_types?name={name}")
	public Call<GetServerTypesResponse> getServerTypesByName(@Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("server_types/{id}")
	public Call<GetServerTypeResponse> getServerType(@Path("id") int id);
}
