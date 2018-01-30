package de.katzen48.hetznercloudjava.services;

import de.katzen48.hetznercloudjava.reponses.sshkeys.GetSshKeyResponse;
import de.katzen48.hetznercloudjava.reponses.sshkeys.GetSshKeysResponse;
import de.katzen48.hetznercloudjava.requests.ChangeNameRequest;
import de.katzen48.hetznercloudjava.requests.CreateSshKeyRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SshKeysService 
{
	@Headers({ "Accept: application/json" })
	@GET("ssh_keys")
	public Call<GetSshKeysResponse> getSshKeys();
	
	@Headers({ "Accept: application/json" })
	@GET("ssh_keys?name={name}")
	public Call<GetSshKeysResponse> getSshKeysByName(@Path("name") String name);
	
	@Headers({ "Accept: application/json" })
	@GET("ssh_keys/{id}")
	public Call<GetSshKeyResponse> getSshKey(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@POST("ssh_keys")
	public Call<GetSshKeyResponse> createSshKey(@Body CreateSshKeyRequest request);
	
	@Headers({ "Accept: application/json" })
	@PUT("ssh_keys/{id}")
	public Call<GetSshKeyResponse> changeName(@Path("id") int id);
	
	@Headers({ "Accept: application/json" })
	@PUT("ssh_keys/{id}")
	public Call<GetSshKeyResponse> changeName(@Path("id") int id, @Body ChangeNameRequest request);
	
	@Headers({ "Accept: application/json" })
	@DELETE("ssh_keys/{id}")
	public Call<Object> delete(@Path("id") int id);
}
