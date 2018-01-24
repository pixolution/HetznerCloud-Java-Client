package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.server.CreateServerResponse;
import de.katzen48.hetznercloudjava.reponses.server.DeleteServerResponse;
import de.katzen48.hetznercloudjava.reponses.server.GetServerResponse;
import de.katzen48.hetznercloudjava.reponses.server.GetServersResponse;
import de.katzen48.hetznercloudjava.requests.CreateServerRequest;
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
	public Call<GetServersResponse> getAllServers();
	
	@Headers({ "Accept: application/json" })
	@GET("servers?{name}")
	public Call<GetServersResponse> getServersByName(@Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("servers/{id}")
	public Call<GetServerResponse> getServer(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("servers")
	public Call<CreateServerResponse> createServer(@Body CreateServerRequest request);
	
	@Headers({ "Accept: application/json" })
	@PUT("servers/{id}")
	public Call<GetServerResponse> changeName(@Path("id") int id, @Query("name") String name);
	
	@Headers({ "Accept: application/json" })
	@DELETE("servers/{id}")
	public Call<DeleteServerResponse> delete(@Path("id") int id);
}
