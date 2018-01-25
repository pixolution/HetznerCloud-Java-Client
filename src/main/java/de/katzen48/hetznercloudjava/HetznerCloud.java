package de.katzen48.hetznercloudjava;

import java.io.IOException;

import de.katzen48.hetznercloudjava.exceptions.APIException;
import de.katzen48.hetznercloudjava.exceptions.BadRequestException;
import de.katzen48.hetznercloudjava.exceptions.RateLimitException;
import de.katzen48.hetznercloudjava.exceptions.UnauthorizedEndpointException;
import de.katzen48.hetznercloudjava.reponses.actions.GetActionResponse;
import de.katzen48.hetznercloudjava.reponses.actions.GetActionsResponse;
import de.katzen48.hetznercloudjava.reponses.datacenters.GetDatacenterResponse;
import de.katzen48.hetznercloudjava.reponses.datacenters.GetDatacentersResponse;
import de.katzen48.hetznercloudjava.reponses.floatingips.GetFloatingIpResponse;
import de.katzen48.hetznercloudjava.reponses.floatingips.GetFloatingIpsResponse;
import de.katzen48.hetznercloudjava.reponses.floatingips.actions.GetFloatingIpActionResponse;
import de.katzen48.hetznercloudjava.reponses.floatingips.actions.GetFloatingIpActionsResponse;
import de.katzen48.hetznercloudjava.reponses.images.GetImageResponse;
import de.katzen48.hetznercloudjava.reponses.images.GetImagesResponse;
import de.katzen48.hetznercloudjava.reponses.isos.GetIsoResponse;
import de.katzen48.hetznercloudjava.reponses.isos.GetIsosResponse;
import de.katzen48.hetznercloudjava.reponses.locations.GetLocationResponse;
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
import de.katzen48.hetznercloudjava.resources.Iso;
import de.katzen48.hetznercloudjava.resources.Location;
import de.katzen48.hetznercloudjava.resources.Pricing;
import de.katzen48.hetznercloudjava.resources.Server;
import de.katzen48.hetznercloudjava.resources.ApiAction;
import de.katzen48.hetznercloudjava.resources.ApiAction.Status;
import de.katzen48.hetznercloudjava.resources.Datacenter;
import de.katzen48.hetznercloudjava.resources.ServerType;
import de.katzen48.hetznercloudjava.resources.SshKey;
import de.katzen48.hetznercloudjava.services.ActionsService;
import de.katzen48.hetznercloudjava.services.DatacentersService;
import de.katzen48.hetznercloudjava.services.FloatingIpActionsService;
import de.katzen48.hetznercloudjava.services.FloatingIpsService;
import de.katzen48.hetznercloudjava.services.ImagesService;
import de.katzen48.hetznercloudjava.services.IsosService;
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
	
	
	public ApiAction[] getActions()
	{
		try 
		{
			return ((Response<GetActionsResponse>) doRequest(retrofit.create(ActionsService.class).getActions())).body().getActions();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ApiAction[] getActions(ApiAction.Status status)
	{
		try 
		{
			return ((Response<GetActionsResponse>) doRequest(retrofit.create(ActionsService.class).getActionsByStatus(status))).body().getActions();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ApiAction[] getSortedActions(String sorting)
	{
		try 
		{
			return ((Response<GetActionsResponse>) doRequest(retrofit.create(ActionsService.class).getSortedActions(sorting))).body().getActions();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ApiAction[] getSortedActions(Status status, String sorting)
	{
		try 
		{
			return ((Response<GetActionsResponse>) doRequest(retrofit.create(ActionsService.class).getSortedActionsByStatus(status, sorting))).body().getActions();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ApiAction getAction(int id)
	{
		try 
		{
			return ((Response<GetActionResponse>) doRequest(retrofit.create(ActionsService.class).getAction(id))).body().getAction();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
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
	
	public ApiAction[] getServerActions(int serverId)
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
	
	public ApiAction[] getSortedServerActions(int serverId, String sorting)
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
	
	public ApiAction[] getServerActionsByStatus(int serverId, ApiAction.Status status)
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
	
	public ApiAction[] getSortedServerActionsByStatus(int serverId, String sorting, ApiAction.Status status)
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
	
	public ApiAction getServerAction(int serverId, int actionId)
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
	
	public ApiAction poweronServer(int serverId)
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
	
	public ApiAction rebootServer(int serverId)
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
	
	public ApiAction resetServer(int serverId)
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
	
	public ApiAction shutdownServer(int serverId)
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
	
	public ApiAction poweroffServer(int serverId)
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
	
	public FloatingIp createFloatingIp(de.katzen48.hetznercloudjava.resources.FloatingIp.Type type, Location homeLocation, int serverId, String description)
	{
		try 
		{
			return ((Response<GetFloatingIpResponse>) doRequest(retrofit.create(FloatingIpsService.class).create(type, homeLocation, serverId, description))).body().getFloatingIp();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public FloatingIp changeFloatingIpDescription(int id)
	{
		try 
		{
			return ((Response<GetFloatingIpResponse>) doRequest(retrofit.create(FloatingIpsService.class).changeDescription(id))).body().getFloatingIp();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public FloatingIp changeFloatingIpDescription(int id, String description)
	{
		try 
		{
			return ((Response<GetFloatingIpResponse>) doRequest(retrofit.create(FloatingIpsService.class).changeDescription(id, description))).body().getFloatingIp();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deleteFloatingIp(int ipId)
	{
		try 
		{
			return ((Response<Object>) doRequest(retrofit.create(FloatingIpsService.class).delete(ipId))).isSuccessful();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public ApiAction[] getFloatingApiActions(int ipId)
	{
		try 
		{
			return ((Response<GetFloatingIpActionsResponse>) doRequest(retrofit.create(FloatingIpActionsService.class).getActions(ipId))).body().getActions();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ApiAction[] getFloatingApiActions(int ipId, String sorting)
	{
		try 
		{
			return ((Response<GetFloatingIpActionsResponse>) doRequest(retrofit.create(FloatingIpActionsService.class).getSortedActions(ipId, sorting))).body().getActions();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ApiAction getFloatingApiAction(int ipId, int actionId)
	{
		try 
		{
			return ((Response<GetFloatingIpActionResponse>) doRequest(retrofit.create(FloatingIpActionsService.class).getAction(ipId, actionId))).body().getAction();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ApiAction assignFloatingIpToServer(int ipId, int serverId)
	{
		try 
		{
			return ((Response<GetFloatingIpActionResponse>) doRequest(retrofit.create(FloatingIpActionsService.class).assignToServer(ipId, serverId))).body().getAction();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ApiAction unassignFloatingIp(int ipId)
	{
		try 
		{
			return ((Response<GetFloatingIpActionResponse>) doRequest(retrofit.create(FloatingIpActionsService.class).unassign(ipId))).body().getAction();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ApiAction changeFloatingIpDnsPtr(int ipId, String ip, String dnsPtr)
	{
		try 
		{
			return ((Response<GetFloatingIpActionResponse>) doRequest(retrofit.create(FloatingIpActionsService.class).changeDnsPtr(ipId, ip, dnsPtr))).body().getAction();
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
	
	public Location[] getLocations(String name)
	{
		try 
		{
			return ((Response<GetLocationsResponse>) doRequest(retrofit.create(LocationsService.class).getLocationsByName(name))).body().getLocations();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Location getLocation(int id)
	{
		try 
		{
			return ((Response<GetLocationResponse>) doRequest(retrofit.create(LocationsService.class).getLocation(id))).body().getLocation();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Datacenter[] getDatacenters()
	{
		try 
		{
			return ((Response<GetDatacentersResponse>) doRequest(retrofit.create(DatacentersService.class).getDatacenters())).body().getDatacenters();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Datacenter[] getDatacenters(String name)
	{
		try 
		{
			return ((Response<GetDatacentersResponse>) doRequest(retrofit.create(DatacentersService.class).getDatacentersByName(name))).body().getDatacenters();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Datacenter getDatacenter(int id)
	{
		try 
		{
			return ((Response<GetDatacenterResponse>) doRequest(retrofit.create(DatacentersService.class).getDatacenter(id))).body().getDatacenter();
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
	
	public Image[] getSortedImages(String sorting)
	{
		try 
		{
			return ((Response<GetImagesResponse>) doRequest(retrofit.create(ImagesService.class).getSortedImages(sorting))).body().getImages();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Image[] getImagesByType(Image.Type type)
	{
		try 
		{
			return ((Response<GetImagesResponse>) doRequest(retrofit.create(ImagesService.class).getImagesByType(type))).body().getImages();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Image[] getBoundImages(String boundTo)
	{
		try 
		{
			return ((Response<GetImagesResponse>) doRequest(retrofit.create(ImagesService.class).getImagesBoundTo(boundTo))).body().getImages();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Image[] getImages(String name)
	{
		try 
		{
			return ((Response<GetImagesResponse>) doRequest(retrofit.create(ImagesService.class).getImagesByName(name))).body().getImages();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Image getImage(int id)
	{
		try 
		{
			return ((Response<GetImageResponse>) doRequest(retrofit.create(ImagesService.class).getImage(id))).body().getImage();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Image updateImage(int id)
	{
		try 
		{
			return ((Response<GetImageResponse>) doRequest(retrofit.create(ImagesService.class).updateImage(id))).body().getImage();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Image updateImageDescription(int id, String description)
	{
		try 
		{
			return ((Response<GetImageResponse>) doRequest(retrofit.create(ImagesService.class).updateImageDescription(id, description))).body().getImage();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Image updateImageType(int id, Type type)
	{
		try 
		{
			return ((Response<GetImageResponse>) doRequest(retrofit.create(ImagesService.class).updateImageType(id, type))).body().getImage();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Image updateImage(int id, String description, Type type)
	{
		try 
		{
			return ((Response<GetImageResponse>) doRequest(retrofit.create(ImagesService.class).updateImage(id, description, type))).body().getImage();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean deleteImage(int id)
	{
		try 
		{
			return ((Response<Object>) doRequest(retrofit.create(ImagesService.class).delete(id))).isSuccessful();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	public Iso[] getIsos()
	{
		try 
		{
			return ((Response<GetIsosResponse>) doRequest(retrofit.create(IsosService.class).getIsos())).body().getIsos();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Iso[] getIsos(String name)
	{
		try 
		{
			return ((Response<GetIsosResponse>) doRequest(retrofit.create(IsosService.class).getIsosByName(name))).body().getIsos();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public Iso getIso(int id)
	{
		try 
		{
			return ((Response<GetIsoResponse>) doRequest(retrofit.create(IsosService.class).getIso(id))).body().getIso();
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
