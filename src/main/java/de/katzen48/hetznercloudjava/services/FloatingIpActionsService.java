package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.floatingips.actions.GetFloatingIpActionResponse;
import de.katzen48.hetznercloudjava.reponses.floatingips.actions.GetFloatingIpActionsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface FloatingIpActionsService 
{
	@Headers({ "Accept: application/json" })
	@GET("floating_ips/{id}/actions")
	public Call<GetFloatingIpActionsResponse> getActions(@Path("id") int ipId);
	
	@Headers({ "Accept: application/json" })
	@GET("floating_ips/{id}/actions?sort={sort}")
	public Call<GetFloatingIpActionsResponse> getSortedActions(@Path("id") int ipId, @Path("sort") String sorting);
	
	@Headers({ "Accept: application/json" })
	@GET("floating_ips/{id}/actions/{action_id}")
	public Call<GetFloatingIpActionResponse> getAction(@Path("id") int ipId, @Path("action_id") int actionId);
	
	@Headers({ "Accept: application/json" })
	@POST("floating_ips/{id}/actions/assign")
	public Call<GetFloatingIpActionResponse> assignToServer(@Path("id") int ipId, @Query("server") int serverId);
	
	@Headers({ "Accept: application/json" })
	@POST("floating_ips/{id}/actions/unassign")
	public Call<GetFloatingIpActionResponse> unassign(@Path("id") int ipId);
	
	@Headers({ "Accept: application/json" })
	@POST("floating_ips/{id}/actions/change_dns_ptr")
	public Call<GetFloatingIpActionResponse> changeDnsPtr(@Path("id") int ipId, @Query("ip") String ip, @Query("dns_ptr") String dnsPtr);
}
