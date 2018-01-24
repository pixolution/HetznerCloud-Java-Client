package de.katzen48.hetznercloudjava;

import java.io.IOException;
import java.util.List;

import de.katzen48.hetznercloudjava.exceptions.APIException;
import de.katzen48.hetznercloudjava.exceptions.BadRequestException;
import de.katzen48.hetznercloudjava.exceptions.RateLimitException;
import de.katzen48.hetznercloudjava.reponses.ServersResponse;
import de.katzen48.hetznercloudjava.resources.Server;
import de.katzen48.hetznercloudjava.services.ServersService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HetznerCloud 
{
	public static final String BASE_URI = "https://api.hetzner.cloud/v1/";
	
	private Retrofit retrofit;
	
	
	private HetznerCloud(String bearerToken)
	{
		OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor()
		{
			@Override
			public okhttp3.Response intercept(Chain chain) throws IOException 
			{
				Request request = chain.request().newBuilder().addHeader("Authorization", "Bearer " + bearerToken).build();
				return chain.proceed(request);
			}
			
		}).build();
		
		retrofit = new Retrofit.Builder().baseUrl(BASE_URI).addConverterFactory(GsonConverterFactory.create()).client(client).build();
	}
	
	public Server[] getServers()
	{
		try 
		{
			return ((Response<ServersResponse>) doRequest(retrofit.create(ServersService.class).getAllServers())).body().getServers();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Response<?> doRequest(Call<?> call) throws APIException
	{
		try 
		{
			Response<Object> response = (Response<Object>) call.execute();
			
			if(!response.isSuccessful())
			{
				switch (response.code()) 
				{
				case 429:
					throw new RateLimitException(Long.parseLong(response.headers().get("RateLimit-Reset")));
				default:
					throw new BadRequestException(call.toString(), response.message());
				}	
			}
			
			return response;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static class Builder
	{
		private String bearerToken;
		
		
		public Builder withToken(String token)
		{
			this.bearerToken = token;
			return this;
		}
		
		public HetznerCloud build()
		{
			return new HetznerCloud(bearerToken);
		}
	}
}
