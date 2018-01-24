package de.katzen48.hetznercloudjava;

import java.io.IOException;

import de.katzen48.hetznercloudjava.exceptions.APIException;
import de.katzen48.hetznercloudjava.exceptions.BadRequestException;
import de.katzen48.hetznercloudjava.exceptions.RateLimitException;
import de.katzen48.hetznercloudjava.reponses.pricing.GetPricingResponse;
import de.katzen48.hetznercloudjava.reponses.server.CreateServerResponse;
import de.katzen48.hetznercloudjava.reponses.server.DeleteServerResponse;
import de.katzen48.hetznercloudjava.reponses.server.GetServerResponse;
import de.katzen48.hetznercloudjava.reponses.server.GetServersResponse;
import de.katzen48.hetznercloudjava.requests.CreateServerRequest;
import de.katzen48.hetznercloudjava.resources.Pricing;
import de.katzen48.hetznercloudjava.resources.Server;
import de.katzen48.hetznercloudjava.services.PricingService;
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
			return ((Response<GetServersResponse>) doRequest(retrofit.create(ServersService.class).getAllServers())).body().getServers();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Server[] getServers(String name)
	{
		try 
		{
			return ((Response<GetServersResponse>) doRequest(retrofit.create(ServersService.class).getServersByName(name))).body().getServers();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Server getServer(int id)
	{
		try 
		{
			return ((Response<GetServerResponse>) doRequest(retrofit.create(ServersService.class).getServer(id))).body().getServer();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public CreateServerResponse createServer(CreateServerRequest request)
	{
		try 
		{
			return ((Response<CreateServerResponse>) doRequest(retrofit.create(ServersService.class).createServer(request))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Server changeServerName(int id, String name)
	{
		try 
		{
			return ((Response<GetServerResponse>) doRequest(retrofit.create(ServersService.class).changeName(id, name))).body().getServer();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public DeleteServerResponse deleteServer(int id)
	{
		try 
		{
			return ((Response<DeleteServerResponse>) doRequest(retrofit.create(ServersService.class).getServer(id))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Pricing getPricing()
	{
		try 
		{
			return ((Response<GetPricingResponse>) doRequest(retrofit.create(PricingService.class).getAll())).body().getPricing();
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
