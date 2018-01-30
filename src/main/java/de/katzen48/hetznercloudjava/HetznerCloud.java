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
import de.katzen48.hetznercloudjava.requests.AssignFloatingIpToServerRequest;
import de.katzen48.hetznercloudjava.requests.AttachServerIsoRequest;
import de.katzen48.hetznercloudjava.requests.ChangeDescriptionRequest;
import de.katzen48.hetznercloudjava.requests.ChangeDnsPtrRequest;
import de.katzen48.hetznercloudjava.requests.ChangeNameRequest;
import de.katzen48.hetznercloudjava.requests.ChangeServerTypeRequest;
import de.katzen48.hetznercloudjava.requests.CreateFloatingIpRequest;
import de.katzen48.hetznercloudjava.requests.CreateServerImageRequest;
import de.katzen48.hetznercloudjava.requests.CreateServerRequest;
import de.katzen48.hetznercloudjava.requests.CreateSshKeyRequest;
import de.katzen48.hetznercloudjava.requests.EnableServerBackupRequest;
import de.katzen48.hetznercloudjava.requests.EnableServerRescueModeRequest;
import de.katzen48.hetznercloudjava.requests.RebuildServerRequest;
import de.katzen48.hetznercloudjava.requests.UpdateImageRequest;
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

/**
 * Main Class for the API. Use the Builder to create an Instance
 */
public class HetznerCloud 
{
	/**
	 * Base URL of the HetznerCloud-API
	 */
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
	
	
	/**
	 * Get all actions of the current project
	 * @return Array of ApiActions
	 */
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
	
	/**
	 * Get all Actions with the provided Status
	 * @param status Preferred status
	 * @return Array of ApiActions
	 */
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
	
	/**
	 * Get all Actions with a custom Sorting
	 * @param sorting Preferred Sorting
	 * @return Array of ApiActions
	 */
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
	
	/**
	 * Get all Actions with a custom Sorting and matching the provied Status
	 * @param status Preferred status
	 * @param sorting Preferred Sorting
	 * @return Array of ApiActions
	 */
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
	
	/**
	 * Get an ApiAction with the specified ID
	 * @param id ID of the Action
	 * @return The ApiAction
	 */
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
	
	/**
	 * Get all your Servers
	 * @return Array of Servers
	 */
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
	
	/**
	 * Get all Servers matching the provided Name
	 * @param name Preferred Name
	 * @return Array of Servers
	 */
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
	
	/**
	 * Get a Server with a specific ID
	 * @param id ID of the Server
	 * @return The Server
	 */
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
	
	/**
	 * Create a Server
	 * @param request Needed Request containing all required Information
	 * @return The Response containing the Server, the ApiAction and the Root Password
	 */
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
	
	/**
	 * Change the Name of a Server
	 * @param id ID of the Server
	 * @param name Name, you want the Server to get
	 * @return The new Server-Object
	 */
	public Server changeServerName(int id, String name)
	{
		try 
		{
			return ((Response<GetServerResponse>) doRequest(retrofit.create(ServersService.class).changeName(id, new ChangeNameRequest(
					name)))).body().getServer();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Delete a Server
	 * @param id ID of the Server
	 * @return If the Server was deleted
	 */
	public boolean deleteServer(int id)
	{
		try 
		{
			return ((Response<DeleteServerResponse>) doRequest(retrofit.create(ServersService.class).delete(id))).body().getError() == null;
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Get all Actions for a specific Server
	 * @param serverId Preferred Server
	 * @return Array of ApiActions
	 */
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
	
	/**
	 * Get all Actions for a specific Server sorted by a custom Sorting
	 * @param serverId Preferred Server
	 * @param sorting Preferred Sorting
	 * @return Array of ApiActions
	 */
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
	
	/**
	 * Get all Actions for a specific Server with the given Status
	 * @param serverId Preferred Server
	 * @param status Preferred Status
	 * @return Array of ApiActions
	 */
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
	
	/**
	 * Get all Actions for a specific Server with a custom Sorting and Status
	 * @param serverId Preferred Server
	 * @param sorting Preferred Sorting
	 * @param status Preferred Status
	 * @return Array of ApiActions
	 */
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
	
	/**
	 * Get an Action for a specific Server
	 * @param serverId Preferred Server
	 * @param actionId Preferred Action
	 * @return The ApiAction
	 */
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
	
	/**
	 * Start a Server
	 * @param serverId Preferred Server
	 * @return The ApiAction
	 */
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
	
	/**
	 * Reboot a Server
	 * @param serverId Preferred Server
	 * @return The ApiAction
	 */
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
	
	/**
	 * Reset a Server
	 * @param serverId Preferred Server
	 * @return The ApiAction
	 */
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
	
	/**
	 * Shutdown a Server
	 * @param serverId Preferred Server
	 * @return The ApiAction
	 */
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
	
	/**
	 * Poweroff a Server
	 * @param serverId Preferred Server
	 * @return The ApiAction
	 */
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
	
	/**
	 * Reset the Root Password of a Server
	 * @param serverId Preferred Server
	 * @return The Response
	 */
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
	
	/**
	 * Enable the Rescue Mode of a Server
	 * @param serverId Preferred Server
	 * @return The Response
	 */
	public EnableRescueModeResponse enableServerRescueMode(int serverId)
	{
		try 
		{
			return ((Response<EnableRescueModeResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.enableRescueMode(serverId, new EnableServerRescueModeRequest.Builder().build()))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Enable the Rescue Mode of a Server with a specific OS
	 * @param serverId Preferred Server
	 * @param osType Preferred OS
	 * @return The Response
	 */
	public EnableRescueModeResponse enableServerRescueMode(int serverId, String osType)
	{
		try 
		{
			return ((Response<EnableRescueModeResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.enableRescueMode(serverId, new EnableServerRescueModeRequest.Builder().withOsType(osType).build()))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Enable the Rescue Mode of a Server and add SSH-Keys
	 * @param serverId Preferred Server
	 * @param sshKeys Your SSH-Keys
	 * @return The Response
	 */
	public EnableRescueModeResponse enableServerRescueMode(int serverId, int[] sshKeys)
	{
		try 
		{
			return ((Response<EnableRescueModeResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.enableRescueMode(serverId, new EnableServerRescueModeRequest.Builder().withSshKeys(sshKeys)
							.build()))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Enable the Rescue Mode of a Server with a specific OS and add SSH-Keys
	 * @param serverId Preferred Server
	 * @param osType Preferred OS
	 * @param sshKeys Your SSH-Keys
	 * @return The Response
	 */
	public EnableRescueModeResponse enableServerRescueMode(int serverId, String osType, int[] sshKeys)
	{
		try 
		{
			return ((Response<EnableRescueModeResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.enableRescueMode(serverId, new EnableServerRescueModeRequest.Builder().withOsType(osType)
							.withSshKeys(sshKeys).build()))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Create a Server Image
	 * @param serverId Preferred Server
	 * @return The Response
	 */
	public CreateImageResponse createServerImage(int serverId)
	{
		try 
		{
			return ((Response<CreateImageResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.createImage(serverId, new CreateServerImageRequest.Builder().build()))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Create a Server Image with a specific Description
	 * @param serverId Preferred Server
	 * @param description Preferred Description
	 * @return The Response
	 */
	public CreateImageResponse createServerImage(int serverId, String description)
	{
		try 
		{
			return ((Response<CreateImageResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.createImage(serverId, new CreateServerImageRequest.Builder().withDescription(description).build()))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Create a Server Image with a specific Type
	 * @param serverId Preferred Server
	 * @param type Preferred Type
	 * @return The Response
	 */
	public CreateImageResponse createServerImage(int serverId, Type type)
	{
		try 
		{
			return ((Response<CreateImageResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.createImage(serverId, new CreateServerImageRequest.Builder().withType(type).build()))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Create a Server Image with a specific Description and a Type
	 * @param serverId Preferred Server
	 * @param description Preferred Description
	 * @param type Preferred Type
	 * @return The Response
	 */
	public CreateImageResponse createServerImage(int serverId, String description, Type type)
	{
		try 
		{
			return ((Response<CreateImageResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.createImage(serverId, new CreateServerImageRequest.Builder().withDescription(description)
							.withType(type).build()))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Rebuild a Server with an Image
	 * @param serverId Preferred Server
	 * @param imageId Preferred Image
	 * @return The Response
	 */
	public RebuildServerResponse rebuildServer(int serverId, int imageId)
	{
		try 
		{
			return ((Response<RebuildServerResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.rebuildServer(serverId, new RebuildServerRequest(imageId)))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Change the Type of a Server
	 * @param serverId Preferred Server
	 * @param type Preferred Type
	 * @return The Response
	 */
	public ServerActionResponse changeServerType(int serverId, String type)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.changeServerType(serverId, new ChangeServerTypeRequest.Builder().withType(type).build()))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Change the Type of a Server
	 * @param serverId Preferred Server
	 * @param type Preferred Type
	 * @param upgradeDisk If you want to upgrade the Disk
	 * @return The Response
	 */
	public ServerActionResponse changeServerType(int serverId, String type, boolean upgradeDisk)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.changeServerType(serverId, new ChangeServerTypeRequest.Builder().withType(type)
							.upgradeDisk(upgradeDisk).build()))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Enable Backups for a Server
	 * @param serverId Preferred Server
	 * @return The Response
	 */
	public ServerActionResponse enableBackup(int serverId)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.enableBackup(serverId))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Enable Backups for a Server during a specific BackupWindow
	 * @param serverId Preferred Server
	 * @param backupWindow Preferred BackupWindow
	 * @return The Response
	 */
	public ServerActionResponse enableBackup(int serverId, String backupWindow)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.enableBackup(serverId, new EnableServerBackupRequest.Builder().withBackupWindow(backupWindow)
							.build()))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Disable Backups for a Server
	 * @param serverId Preferred Server
	 * @return The Response
	 */
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
	
	/**
	 * Attach an Iso to a Server
	 * @param serverId Preferred Server
	 * @param iso Preferred Iso
	 * @return The Response
	 */
	public ServerActionResponse attachIsoToServer(int serverId, int iso)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.attachIso(serverId, new AttachServerIsoRequest(iso)))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Detach an Iso from a Server
	 * @param serverId Preferred Server
	 * @return The Response
	 */
	public ServerActionResponse detachIsoFromServer(int serverId)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.detachIso(serverId))).body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Change the DNS-PTR of a Server
	 * @param serverId Preferred Server
	 * @param ip Preferred IP
	 * @param dnsPtr Preferred DNS-PTR
	 * @return The Response
	 */
	public ServerActionResponse changeServerDnsPtr(int serverId, String ip, String dnsPtr)
	{
		try 
		{
			return ((Response<ServerActionResponse>) doRequest(retrofit.create(ServerActionsService.class)
					.changeDnsPtr(serverId, new ChangeDnsPtrRequest.Builder().withIp(ip).withDnsPtr(dnsPtr).build())))
					.body();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}	
	
	/**
	 * Get all FloatingIPs
	 * @return Array of FloatingIPs
	 */
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
	
	/**
	 * Get a FloatingIP
	 * @param id Preferred IP
	 * @return The FloatingIP
	 */
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
	
	/**
	 * Create a FloatingIP
	 * @param type Preferred Type
	 * @param homeLocation Preferred HomeLocation
	 * @param serverId Preferred Server
	 * @param description Preferred Description
	 * @return The FloatingIP
	 */
	public FloatingIp createFloatingIp(de.katzen48.hetznercloudjava.resources.FloatingIp.Type type, String homeLocation, int serverId, String description)
	{
		try 
		{
			return ((Response<GetFloatingIpResponse>) doRequest(retrofit.create(FloatingIpsService.class)
					.create(new CreateFloatingIpRequest.Builder().withType(type).withLocation(homeLocation)
							.withServer(serverId).withDescription(description).build()))).body().getFloatingIp();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Change the Description of a FloatingIP
	 * @param id Preferred FloatingIP
	 * @return The new FloatingIP Object
	 */
	public FloatingIp changeFloatingIpDescription(int id)
	{
		try 
		{
			return ((Response<GetFloatingIpResponse>) doRequest(retrofit.create(FloatingIpsService.class)
					.changeDescription(id, new ChangeDescriptionRequest(null)))).body().getFloatingIp();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Change the Description of a FloatingIP
	 * @param id Preferred FloatingIP
	 * @param description Preferred Description
	 * @return
	 */
	public FloatingIp changeFloatingIpDescription(int id, String description)
	{
		try 
		{
			return ((Response<GetFloatingIpResponse>) doRequest(retrofit.create(FloatingIpsService.class)
					.changeDescription(id, new ChangeDescriptionRequest(description)))).body().getFloatingIp();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Delete a FloatingIP
	 * @param ipId Preferred FloatingIP
	 * @return If the FloatingIP was deleted
	 */
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
	
	/**
	 * Get all Actions for a specific FloatingIP
	 * @param ipId Preferred FloatingIP
	 * @return Array of ApiActions
	 */
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
	
	/**
	 * Get all Actions for a specific FloatingIP
	 * @param ipId Preferred FloatingIP
	 * @param sorting Preferred Sorting
	 * @return Array of ApiActions
	 */
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
	
	/**
	 * Get a specific Action for a FloatingIP
	 * @param ipId Preferred FloatingIP
	 * @param actionId Preferred Action
	 * @return The ApiAction
	 */
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
	
	/**
	 * Assign a FloatingIP to a Server
	 * @param ipId Preferred FloatingIP
	 * @param serverId Preferred Server
	 * @return The ApiAction
	 */
	public ApiAction assignFloatingIpToServer(int ipId, int serverId)
	{
		try 
		{
			return ((Response<GetFloatingIpActionResponse>) doRequest(retrofit.create(FloatingIpActionsService.class)
					.assignToServer(ipId, new AssignFloatingIpToServerRequest(serverId)))).body().getAction();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Unassign a FloatingIP
	 * @param ipId Preferred FloatingIP
	 * @return The ApiAction
	 */
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
	
	/**
	 * Change the DnsPtr of a FloatingIP
	 * @param ipId Preferred FloatingIP
	 * @param ip IP of the DnsPtr
	 * @param dnsPtr The DnsPtr
	 * @return The ApiAction
	 */
	public ApiAction changeFloatingIpDnsPtr(int ipId, String ip, String dnsPtr)
	{
		try 
		{
			return ((Response<GetFloatingIpActionResponse>) doRequest(retrofit.create(FloatingIpActionsService.class)
					.changeDnsPtr(ipId, new ChangeDnsPtrRequest.Builder().withIp(ip).withDnsPtr(dnsPtr).build())))
					.body().getAction();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Get all your SshKeys
	 * @return Array of SshKeys
	 */
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
	
	/**
	 * Get all your SshKeys with a specific name
	 * @param name Preferred Name
	 * @return Array of SshKeys
	 */
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
	
	/**
	 * Get a specific SshKey
	 * @param id Preferred Key
	 * @return The SshKey
	 */
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
	
	/**
	 * Create a SshLey
	 * @param name Preferred Name
	 * @param publicKey Preferred PublicKey
	 * @return The SshKey
	 */
	public SshKey createSshKey(String name, String publicKey)
	{
		try 
		{
			return ((Response<GetSshKeyResponse>) doRequest(retrofit.create(SshKeysService.class)
					.createSshKey(new CreateSshKeyRequest(name, publicKey)))).body().getSshKey();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Change the Name of a SshKey
	 * @param id Preferred Key
	 * @return The new SshKey-Object
	 */
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
	
	/**
	 * Change the Name of a SshKey
	 * @param id Preferred Key
	 * @param name New Name
	 * @return The new SshKey-Object
	 */
	public SshKey changeSshKeyName(int id, String name)
	{
		try 
		{
			return ((Response<GetSshKeyResponse>) doRequest(retrofit.create(SshKeysService.class)
					.changeName(id, new ChangeNameRequest(name)))).body().getSshKey();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Delete a SshKey
	 * @param id Preferred SshKey
	 * @return If the SshKey was deleted
	 */
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
	
	/**
	 * Get all ServerTypes
	 * @return Array of ServerTypes
	 */
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
	
	/**
	 * Get all ServerTypes with a specific Name
	 * @param name Preferred Name
	 * @return Array of ServerTypes
	 */
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
	
	/**
	 * Get a specific ServerType
	 * @param id Preferred ServerType
	 * @return The ServerType
	 */
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
	
	/**
	 * Get all Locations
	 * @return Array of Locations
	 */
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
	
	/**
	 * Get all Locations with a specific Name
	 * @param name Preferred Name
	 * @return Array of Locations
	 */
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
	
	/**
	 * Get a specific Location
	 * @param id Preferred Location
	 * @return The Location
	 */
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
	
	/**
	 * Get all Datacenters
	 * @return Array of Datacenters
	 */
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
	
	/**
	 * Get all Datacenters with a specific Name
	 * @param name Preferred Name
	 * @return Array of Datacenters
	 */
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
	
	/**
	 * Get a specific Datacenter
	 * @param id Preferred Datacenter
	 * @return The Datacenter
	 */
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
	
	/**
	 * Get all Images
	 * @return Array of Images
	 */
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
	
	/**
	 * Get all Images sorted
	 * @param sorting Preferred Sorting
	 * @return Array of Images
	 */
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
	
	/**
	 * Get all Images with a specific Type
	 * @param type Preferred Type
	 * @return Array of Images
	 */
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
	
	/**
	 * Get all Images bound to a Server
	 * @param boundTo Preferred Server
	 * @return Array of Images
	 */
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
	
	/**
	 * Get all Images with a specific Name
	 * @param name Preferred Name
	 * @return Array of Images
	 */
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
	
	/**
	 * Get a specific Image
	 * @param id Preferred Image
	 * @return The Image
	 */
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
	
	/**
	 * Update an Image 
	 * @param id Preferred Image
	 * @return The new Image-Object
	 */
	public Image updateImage(int id)
	{
		try 
		{
			return ((Response<GetImageResponse>) doRequest(retrofit.create(ImagesService.class)
					.updateImage(id, new UpdateImageRequest.Builder().build()))).body().getImage();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Update the Description of an Image
	 * @param id Preferred Image
	 * @param description The new Description
	 * @return The new Image-Object
	 */
	public Image updateImageDescription(int id, String description)
	{
		try 
		{
			return ((Response<GetImageResponse>) doRequest(retrofit.create(ImagesService.class)
					.updateImage(id, new UpdateImageRequest.Builder().withDescription(description).build()))).body()
					.getImage();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Update the Type of an Image
	 * @param id Preferred Image
	 * @param type The new Type
	 * @return The new Image-Object
	 */
	public Image updateImageType(int id, Type type)
	{
		try 
		{
			return ((Response<GetImageResponse>) doRequest(retrofit.create(ImagesService.class)
					.updateImage(id, new UpdateImageRequest.Builder().withType(type).build()))).body().getImage();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Update the Description and Type of an Image
	 * @param id Preferred Image
	 * @param description The new Description
	 * @param type The new Type
	 * @return The new Image-Object
	 */
	public Image updateImage(int id, String description, Type type)
	{
		try 
		{
			return ((Response<GetImageResponse>) doRequest(retrofit.create(ImagesService.class)
					.updateImage(id, new UpdateImageRequest.Builder().withDescription(description).withType(type)
							.build()))).body().getImage();
		} 
		catch (APIException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Delete an Image
	 * @param id Preferred Image
	 * @return If the Image was deleted
	 */
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
	
	/**
	 * Get all Isos
	 * @return Array of Isos
	 */
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
	
	/**
	 * Get all Isos with a specific Name
	 * @param name Preferred Name
	 * @return Array of Isos
	 */
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
	
	/**
	 * Get a specific Iso
	 * @param id Preferred Iso
	 * @return The Iso
	 */
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
	
	/**
	 * Get all Pricings
	 * @return The Pricings
	 */
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
	
	/**
	 * Do a Request to the API
	 * @param call The call you want to execute
	 * @return The Response
	 * @throws APIException Exception when Call failed
	 */
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
	
	/**
	 * Use this Builder to create your Object.
	 * You can provide your Token, for access to the API.
	 * @author Katzen48
	 * @since 1.0
	 */
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
