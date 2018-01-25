package de.katzen48.hetznercloudjava;

import java.io.IOException;

import de.katzen48.hetznercloudjava.exceptions.APIException;
import de.katzen48.hetznercloudjava.exceptions.BadRequestException;
import de.katzen48.hetznercloudjava.exceptions.RateLimitException;
import de.katzen48.hetznercloudjava.exceptions.UnauthorizedEndpointException;
import de.katzen48.hetznercloudjava.reponses.floatingips.GetFloatingIpResponse;
import de.katzen48.hetznercloudjava.reponses.floatingips.GetFloatingIpsResponse;
import de.katzen48.hetznercloudjava.reponses.images.GetImagesResponse;
import de.katzen48.hetznercloudjava.reponses.locations.GetLocationsResponse;
import de.katzen48.hetznercloudjava.reponses.pricing.GetPricingResponse;
import de.katzen48.hetznercloudjava.reponses.server.CreateServerResponse;
import de.katzen48.hetznercloudjava.reponses.server.DeleteServerResponse;
import de.katzen48.hetznercloudjava.reponses.server.GetServerResponse;
import de.katzen48.hetznercloudjava.reponses.server.GetServersResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.CreateImageResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.EnableRescueModeResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.RebuildServerResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.ResetPasswordResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.ServerActionResponse;
import de.katzen48.hetznercloudjava.reponses.server.actions.ServerActionsResponse;
import de.katzen48.hetznercloudjava.reponses.servertypes.GetServerTypeResponse;
import de.katzen48.hetznercloudjava.reponses.servertypes.GetServerTypesResponse;
import de.katzen48.hetznercloudjava.reponses.sshkeys.GetSshKeyResponse;
import de.katzen48.hetznercloudjava.reponses.sshkeys.GetSshKeysResponse;
import de.katzen48.hetznercloudjava.requests.CreateServerRequest;
import de.katzen48.hetznercloudjava.resources.FloatingIp;
import de.katzen48.hetznercloudjava.resources.Image;
import de.katzen48.hetznercloudjava.resources.Image.Type;
import de.katzen48.hetznercloudjava.resources.Location;
import de.katzen48.hetznercloudjava.resources.Pricing;
import de.katzen48.hetznercloudjava.resources.Server;
import de.katzen48.hetznercloudjava.resources.ServerAction;
import de.katzen48.hetznercloudjava.resources.ServerType;
import de.katzen48.hetznercloudjava.resources.SshKey;
import de.katzen48.hetznercloudjava.services.FloatingIpsService;
import de.katzen48.hetznercloudjava.services.ImagesService;
import de.katzen48.hetznercloudjava.services.LocationsService;
import de.katzen48.hetznercloudjava.services.PricingService;
import de.katzen48.hetznercloudjava.services.ServerActionsService;
import de.katzen48.hetznercloudjava.services.ServerTypesService;
import de.katzen48.hetznercloudjava.services.ServersService;
import de.katzen48.hetznercloudjava.services.SshKeysService;
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
			return ((Response<GetServersResponse>) doRequest(retrofit.create(ServersService.class).getServers())).body().getServers();
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
			return ((Response<DeleteServerResponse>) doRequest(retrofit.create(ServersService.class).delete(id))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerAction[] getServerActions(int serverId)
	{
		try 
		{
			return ((Response<ServerActionsResponse>) doRequest(retrofit.create(ServerActionsService.class).getServerActions(serverId))).body().getActions();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerAction[] getSortedServerActions(int serverId, String sorting)
	{
		try 
		{
			return ((Response<ServerActionsResponse>) doRequest(retrofit.create(ServerActionsService.class).getSortedServerActions(serverId, sorting))).body().getActions();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerAction[] getServerActionsByStatus(int serverId, ServerAction.Status status)
	{
		try 
		{
			return ((Response<ServerActionsResponse>) doRequest(retrofit.create(ServerActionsService.class).getServerActionsByStatus(serverId, status))).body().getActions();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerAction[] getSortedServerActionsByStatus(int serverId, String sorting, ServerAction.Status status)
	{
		try 
		{
			return ((Response<ServerActionsResponse>) doRequest(retrofit.create(ServerActionsService.class).getSortedServerActionsByStatus(serverId, status, sorting))).body().getActions();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerAction getServerAction(int serverId, int actionId)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).getServerAction(serverId, actionId))).body().getAction();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerAction poweronServer(int serverId)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).poweronServer(serverId))).body().getAction();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerAction rebootServer(int serverId)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).rebootServer(serverId))).body().getAction();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerAction resetServer(int serverId)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).resetServer(serverId))).body().getAction();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerAction shutdownServer(int serverId)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).shutdownServer(serverId))).body().getAction();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerAction poweroffServer(int serverId)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).poweroffServer(serverId))).body().getAction();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ResetPasswordResponse resetServerPassword(int serverId)
	{
		try 
		{
			return ((Response<ResetPasswordResponse>) doRequest(retrofit.create(ServerActionsService.class).resetPassword(serverId))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public EnableRescueModeResponse enableServerRescueMode(int serverId)
	{
		try 
		{
			return ((Response<EnableRescueModeResponse>) doRequest(retrofit.create(ServerActionsService.class).enableRescueMode(serverId))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public EnableRescueModeResponse enableServerRescueMode(int serverId, String osType)
	{
		try 
		{
			return ((Response<EnableRescueModeResponse>) doRequest(retrofit.create(ServerActionsService.class).enableRescueMode(serverId, osType))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public EnableRescueModeResponse enableServerRescueMode(int serverId, int[] sshKeys)
	{
		try 
		{
			return ((Response<EnableRescueModeResponse>) doRequest(retrofit.create(ServerActionsService.class).enableRescueMode(serverId, sshKeys))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public EnableRescueModeResponse enableServerRescueMode(int serverId, String osType, int[] sshKeys)
	{
		try 
		{
			return ((Response<EnableRescueModeResponse>) doRequest(retrofit.create(ServerActionsService.class).enableRescueMode(serverId, osType, sshKeys))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public CreateImageResponse createServerImage(int serverId)
	{
		try 
		{
			return ((Response<CreateImageResponse>) doRequest(retrofit.create(ServerActionsService.class).createImage(serverId))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public CreateImageResponse createServerImage(int serverId, String description)
	{
		try 
		{
			return ((Response<CreateImageResponse>) doRequest(retrofit.create(ServerActionsService.class).createImage(serverId, description))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public CreateImageResponse createServerImage(int serverId, Type type)
	{
		try 
		{
			return ((Response<CreateImageResponse>) doRequest(retrofit.create(ServerActionsService.class).createImage(serverId, type))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public CreateImageResponse createServerImage(int serverId, String description, Type type)
	{
		try 
		{
			return ((Response<CreateImageResponse>) doRequest(retrofit.create(ServerActionsService.class).createImage(serverId, description, type))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public RebuildServerResponse rebuildServer(int serverId, int imageId)
	{
		try 
		{
			return ((Response<RebuildServerResponse>) doRequest(retrofit.create(ServerActionsService.class).rebuildServer(serverId, imageId))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerActionResponse changeServerType(int serverId, String type)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).changeServerType(serverId, type))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerActionResponse changeServerType(int serverId, String type, boolean upgradeDisk)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).changeServerType(serverId, type, upgradeDisk))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerActionResponse enableBackup(int serverId)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).enableBackup(serverId))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerActionResponse enableBackup(int serverId, String backupWindow)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).enableBackup(serverId, backupWindow))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerActionResponse disableBackup(int serverId)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).disableBackup(serverId))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerActionResponse attachIsoToServer(int serverId, int iso)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).attachIso(serverId, iso))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerActionResponse detachIsoFromServer(int serverId)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).detachIso(serverId))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerActionResponse changeServerDnsPtr(int serverId, String ip, String dnsPtr)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class).changeDnsPtr(serverId, ip, dnsPtr))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}	
	
	public FloatingIp[] getFloatingIps()
	{
		try 
		{
			return ((Response<GetFloatingIpsResponse>) doRequest(retrofit.create(FloatingIpsService.class).getFloatingIps())).body().getFloatingIps();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public FloatingIp getFloatingIp(int id)
	{
		try 
		{
			return ((Response<GetFloatingIpResponse>) doRequest(retrofit.create(FloatingIpsService.class).getFloatingIp(id))).body().getFloatingIp();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public SshKey[] getSshKeys()
	{
		try 
		{
			return ((Response<GetSshKeysResponse>) doRequest(retrofit.create(SshKeysService.class).getSshKeys())).body().getSshKeys();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public SshKey[] getSshKeys(String name)
	{
		try 
		{
			return ((Response<GetSshKeysResponse>) doRequest(retrofit.create(SshKeysService.class).getSshKeysByName(name))).body().getSshKeys();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public SshKey getSshKey(int id)
	{
		try 
		{
			return ((Response<GetSshKeyResponse>) doRequest(retrofit.create(SshKeysService.class).getSshKey(id))).body().getSshKey();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public SshKey createSshKey(String name, String publicKey)
	{
		try 
		{
			return ((Response<GetSshKeyResponse>) doRequest(retrofit.create(SshKeysService.class).createSshKey(name, publicKey))).body().getSshKey();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public SshKey changeSshKeyName(int id)
	{
		try 
		{
			return ((Response<GetSshKeyResponse>) doRequest(retrofit.create(SshKeysService.class).changeName(id))).body().getSshKey();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public SshKey changeSshKeyName(int id, String name)
	{
		try 
		{
			return ((Response<GetSshKeyResponse>) doRequest(retrofit.create(SshKeysService.class).changeName(id, name))).body().getSshKey();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deleteSshKey(int id)
	{
		try 
		{
			return ((Response<Object>) doRequest(retrofit.create(SshKeysService.class).delete(id))).isSuccessful();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public ServerType[] getServerTypes()
	{
		try 
		{
			return ((Response<GetServerTypesResponse>) doRequest(retrofit.create(ServerTypesService.class).getServerTypes())).body().getServerTypes();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerType[] getServerTypes(String name)
	{
		try 
		{
			return ((Response<GetServerTypesResponse>) doRequest(retrofit.create(ServerTypesService.class).getServerTypesByName(name))).body().getServerTypes();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ServerType getServerType(int id)
	{
		try 
		{
			return ((Response<GetServerTypeResponse>) doRequest(retrofit.create(ServerTypesService.class).getServerType(id))).body().getServerType();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Location[] getLocations()
	{
		try 
		{
			return ((Response<GetLocationsResponse>) doRequest(retrofit.create(LocationsService.class).getLocations())).body().getLocations();
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
			return ((Response<GetPricingResponse>) doRequest(retrofit.create(PricingService.class).getPricing())).body().getPricing();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Image[] getImages()
	{
		try 
		{
			return ((Response<GetImagesResponse>) doRequest(retrofit.create(ImagesService.class).getImages())).body().getImages();
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
				case 401:
					throw new UnauthorizedEndpointException(call.request().url().url().toString());
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
