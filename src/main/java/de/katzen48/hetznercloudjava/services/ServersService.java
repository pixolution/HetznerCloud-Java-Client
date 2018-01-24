package de.katzen48.hetznercloudjava.services;

import java.util.List;

import de.katzen48.hetznercloudjava.reponses.ServersResponse;
import de.katzen48.hetznercloudjava.resources.Server;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServersService 
{
	@Headers({ "Accept: application/json" })
	@GET("servers")
	public Call<ServersResponse> getAllServers();
	
	@Headers({ "Accept: application/json" })
	@GET("servers?{name}")
	public Call<List<Server>> getServersByName(@Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("servers/{id}")
	public Call<Server> getServer(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("servers")
	public Call<Server> createServer(@Body Server server);
	
	@Headers({ "Accept: application/json" })
	@PUT("servers/{id}")
	public Call<Server> changeName(@Path("id") int id, @Query("name") String name);
	
	@Headers({ "Accept: application/json" })
	@DELETE("servers/{id}")
	public Call<?> delete(@Path("id") int id);
}
