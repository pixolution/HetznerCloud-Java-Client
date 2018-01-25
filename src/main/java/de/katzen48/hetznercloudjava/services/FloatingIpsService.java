package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.floatingips.GetFloatingIpResponse;
import de.katzen48.hetznercloudjava.reponses.floatingips.GetFloatingIpsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface FloatingIpsService 
{
	@Headers({ "Accept: application/json" })
	@GET("floating_ips")
	public Call<GetFloatingIpsResponse> getFloatingIps();
	
	@Headers({ "Accept: application/json" })
	@GET("floating_ips/{id}")
	public Call<GetFloatingIpResponse> getFloatingIp(@Path("id") int id);
}
