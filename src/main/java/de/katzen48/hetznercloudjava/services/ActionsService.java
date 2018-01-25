package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.actions.GetActionResponse;
import de.katzen48.hetznercloudjava.reponses.actions.GetActionsResponse;
import de.katzen48.hetznercloudjava.resources.ApiAction.Status;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface ActionsService 
{
	@Headers({ "Accept: application/json" })
	@GET("actions")
	public Call<GetActionsResponse> getActions();
	
	@Headers({ "Accept: application/json" })
	@GET("actions?status={status}")
	public Call<GetActionsResponse> getActionsByStatus(@Path("status") Status status);
	
	@Headers({ "Accept: application/json" })
	@GET("actions?sort={sorting}")
	public Call<GetActionsResponse> getSortedActions(@Path("sorting") String sorting);
	
	@Headers({ "Accept: application/json" })
	@GET("actions?status={status}&sort={sorting}")
	public Call<GetActionsResponse> getSortedActionsByStatus(@Path("status") Status status, @Path("sorting") String sorting);
	
	@Headers({ "Accept: application/json" })
	@GET("actions/{id}")
	public Call<GetActionResponse> getAction(@Path("id") int id);
}
