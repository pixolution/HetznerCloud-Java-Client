package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.isos.GetIsoResponse;
import de.katzen48.hetznercloudjava.reponses.isos.GetIsosResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface IsosService 
{
	@Headers({ "Accept: application/json" })
	@GET("isos")
	public Call<GetIsosResponse> getIsos();
	
	@Headers({ "Accept: application/json" })
	@GET("isos?name={name}")
	public Call<GetIsosResponse> getIsosByName(@Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("isos/{id}")
	public Call<GetIsoResponse> getIso(@Path("id") int id);
}
