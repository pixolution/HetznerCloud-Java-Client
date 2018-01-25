package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.floatingips.GetFloatingIpResponse;
import de.katzen48.hetznercloudjava.reponses.floatingips.GetFloatingIpsResponse;
import de.katzen48.hetznercloudjava.resources.FloatingIp.Type;
import de.katzen48.hetznercloudjava.resources.Location;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FloatingIpsService 
{
	@Headers({ "Accept: application/json" })
	@GET("floating_ips")
	public Call<GetFloatingIpsResponse> getFloatingIps();
	
	@Headers({ "Accept: application/json" })
	@GET("floating_ips/{id}")
	public Call<GetFloatingIpResponse> getFloatingIp(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("floating_ips")
	public Call<GetFloatingIpsResponse> create(@Query("type") Type type);
	
	@Headers({ "Accept: application/json" })
	@POST("floating_ips")
	public Call<GetFloatingIpsResponse> create(@Query("type") Type type, @Query("home_location") Location homeLocation);
	
	@Headers({ "Accept: application/json" })
	@POST("floating_ips")
	public Call<GetFloatingIpsResponse> create(@Query("type") Type type, @Query("server") int serverId);
	
	@Headers({ "Accept: application/json" })
	@POST("floating_ips")
	public Call<GetFloatingIpsResponse> create(@Query("type") Type type, @Query("description") String description);
	
	@Headers({ "Accept: application/json" })
	@POST("floating_ips")
	public Call<GetFloatingIpsResponse> create(@Query("type") Type type, @Query("home_location") Location homeLocation, @Query("server") int serverId);
	
	@Headers({ "Accept: application/json" })
	@POST("floating_ips")
	public Call<GetFloatingIpsResponse> create(@Query("type") Type type, @Query("home_location") Location homeLocation, @Query("server") int serverId, @Query("description") String description);
	
	@Headers({ "Accept: application/json" })
	@POST("floating_ips")
	public Call<GetFloatingIpsResponse> create(@Query("type") Type type, @Query("home_location") Location homeLocation, @Query("description") String description);
	
	@Headers({ "Accept: application/json" })
	@PUT("floating_ips/{id}")
	public Call<GetFloatingIpResponse> changeDescription(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@PUT("floating_ips/{id}")
	public Call<GetFloatingIpResponse> changeDescription(@Path("id") int id, @Query("description") String description);
	
	@Headers({ "Accept: application/json" })
	@DELETE("floating_ips/{id}")
	public Call<Object> delete(@Path("id") int id);
}
