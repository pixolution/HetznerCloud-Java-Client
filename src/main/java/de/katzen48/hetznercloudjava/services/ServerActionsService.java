package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.server.actions.EnableRescueModeResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.ResetPasswordResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.ServerActionResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.ServerActionsResponse;
import de.katzen48.hetznercloudjava.resources.Server.Status;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServerActionsService 
{
	@Headers({ "Accept: application/json" })
	@GET("servers/{id}/actions")
	public Call<ServerActionsResponse> getAllServerActions(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@GET("servers/{id}/actions?status={status}")
	public Call<ServerActionsResponse> getServerActionsByStatus(@Path("id") int id, @Path("status") Status status);
	
	@Headers({ "Accept: application/json" })
	@GET("servers/{id}/actions?sort={sort}")
	public Call<ServerActionsResponse> getSortedServerActions(@Path("id") int id, @Path("sort") String sortBy);
	
	@Headers({ "Accept: application/json" })
	@GET("servers/{id}/actions?status={status}&sort={sort}")
	public Call<ServerActionsResponse> getSortedServerActionsByStatusA(@Path("id") int id, @Path("status") Status status, @Path("sort") String sortBy);
	
	@Headers({ "Accept: application/json" })
	@GET("servers/{id}/actions/{action_id}")
	public Call<ServerActionResponse> getServerAction(@Path("id") int id, @Path("action_id") int actionId);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/poweron")
	public Call<ServerActionResponse> powerOnServer(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/reboot")
	public Call<ServerActionResponse> rebootServer(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/reset")
	public Call<ServerActionResponse> resetServer(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/shutdown")
	public Call<ServerActionResponse> shutdownServer(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/poweroff")
	public Call<ServerActionResponse> poweroffServer(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/reset_password")
	public Call<ResetPasswordResponse> resetPassword(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/enable_rescue")
	public Call<EnableRescueModeResponse> enableRescueMode(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/enable_rescue")
	public Call<EnableRescueModeResponse> enableRescueMode(@Path("id") int id, @Query("type") String osType);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/enable_rescue")
	public Call<EnableRescueModeResponse> enableRescueMode(@Path("id") int id, @Query("ssh_keys") int[] sshKeys);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/enable_rescue")
	public Call<EnableRescueModeResponse> enableRescueMode(@Path("id") int id, @Query("type") String osType, @Query("ssh_keys") int[] sshKeys);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/disable_rescue")
	public Call<EnableRescueModeResponse> disableRescueMode(@Path("id") int id);
}
