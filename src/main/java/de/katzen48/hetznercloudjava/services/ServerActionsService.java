package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.server.actions.CreateImageResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.EnableRescueModeResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.RebuildServerResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.ResetPasswordResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.ServerActionResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.ServerActionsResponse;
import de.katzen48.hetznercloudjava.requests.AttachServerIsoRequest;
import de.katzen48.hetznercloudjava.requests.ChangeDnsPtrRequest;
import de.katzen48.hetznercloudjava.requests.ChangeServerTypeRequest;
import de.katzen48.hetznercloudjava.requests.CreateServerImageRequest;
import de.katzen48.hetznercloudjava.requests.EnableServerBackupRequest;
import de.katzen48.hetznercloudjava.requests.EnableServerRescueModeRequest;
import de.katzen48.hetznercloudjava.requests.RebuildServerRequest;
import de.katzen48.hetznercloudjava.resources.ApiAction.Status;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerActionsService 
{
	@Headers({ "Accept: application/json" })
	@GET("servers/{id}/actions")
	public Call<ServerActionsResponse> getServerActions(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@GET("servers/{id}/actions?status={status}")
	public Call<ServerActionsResponse> getServerActionsByStatus(@Path("id") int id, @Path("status") Status status);
	
	@Headers({ "Accept: application/json" })
	@GET("servers/{id}/actions?sort={sort}")
	public Call<ServerActionsResponse> getSortedServerActions(@Path("id") int id, @Path("sort") String sortBy);
	
	@Headers({ "Accept: application/json" })
	@GET("servers/{id}/actions?status={status}&sort={sort}")
	public Call<ServerActionsResponse> getSortedServerActionsByStatus(@Path("id") int id, @Path("status") Status status, @Path("sort") String sortBy);
	
	@Headers({ "Accept: application/json" })
	@GET("servers/{id}/actions/{action_id}")
	public Call<ServerActionResponse> getServerAction(@Path("id") int id, @Path("action_id") int actionId);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/poweron")
	public Call<ServerActionResponse> poweronServer(@Path("id") int id);
	
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
	public Call<EnableRescueModeResponse> enableRescueMode(@Path("id") int id, @Body EnableServerRescueModeRequest request);
	
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/create_image")
	public Call<CreateImageResponse> createImage(@Path("id") int id, @Body CreateServerImageRequest request);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/rebuild")
	public Call<RebuildServerResponse> rebuildServer(@Path("id") int id, @Body RebuildServerRequest request);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/change_type")
	public Call<ServerActionResponse> changeServerType(@Path("id") int id, @Body ChangeServerTypeRequest request);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/enable_backup")
	public Call<ServerActionResponse> enableBackup(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/enable_backup")
	public Call<ServerActionResponse> enableBackup(@Path("id") int id, @Body EnableServerBackupRequest request);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/disable_backup")
	public Call<ServerActionResponse> disableBackup(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/attach_iso")
	public Call<ServerActionResponse> attachIso(@Path("id") int id, @Body AttachServerIsoRequest request);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/detach_iso")
	public Call<ServerActionResponse> detachIso(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("servers/{id}/actions/change_dns_ptr")
	public Call<ServerActionResponse> changeDnsPtr(@Path("id") int id, @Body ChangeDnsPtrRequest request);
}
