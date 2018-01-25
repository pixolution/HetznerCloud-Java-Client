package de.katzen48.hetznercloudjava.resources;

import java.sql.Timestamp;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.HetznerCloud;
import de.katzen48.hetznercloudjava.resources.Image.Type;

public class Server 
{
	private int id;
	private String name;
	private Status status;
	private Timestamp created;
	@SerializedName("public_net")
	private Network network;
	@SerializedName("server_type")
	private ServerType serverType;
	private Datacenter datacenter;
	private Image image;
	private Iso iso;
	@SerializedName("rescue_enabled")
	private boolean rescueEnabled;
	private boolean locked;
	@SerializedName("backup_window")
	private String backupWindow;
	@SerializedName("outgoing_traffic")
	private long outgoingTraffic;
	@SerializedName("ingoing_traffic")
	private long ingoingTraffic;
	@SerializedName("included_traffic")
	private long includedTraffic;

	
	public boolean changeName(HetznerCloud cloud, String name)
	{
		Server server = cloud.changeServerName(id, name);
		if(!server.getName().equals(name))
		{
			name = server.getName();
			return true;
		}
		return false;
	}
	
	public boolean delete(HetznerCloud cloud)
	{
		return cloud.deleteServer(id).getAction().getError() == null;
	}
	
	public ServerAction[] getActions(HetznerCloud cloud)
	{
		return cloud.getServerActions(id);
	}
	
	public ServerAction getServerAction(HetznerCloud cloud, int id)
	{
		return cloud.getServerAction(this.id, id);
	}
	
	public boolean poweron(HetznerCloud cloud)
	{
		return cloud.poweronServer(id).getError() == null;
	}
	
	public boolean reboot(HetznerCloud cloud)
	{
		return cloud.rebootServer(id).getError() == null;
	}
	
	public boolean reset(HetznerCloud cloud)
	{
		return cloud.resetServer(id).getError() == null;
	}

	public boolean shutdown(HetznerCloud cloud)
	{
		return cloud.shutdownServer(id).getError() == null;
	}
	
	public boolean poweroff(HetznerCloud cloud)
	{
		return cloud.poweroffServer(id).getError() == null;
	}
	
	public String resetServerPassword(HetznerCloud cloud)
	{
		return cloud.resetServerPassword(id).getRootPassword();
	}
	
	public String enableRescueMode(HetznerCloud cloud)
	{
		return cloud.enableServerRescueMode(id).getRootPassword();
	}
	
	public String enableRescueMode(HetznerCloud cloud, String osType)
	{
		return cloud.enableServerRescueMode(id, osType).getRootPassword();
	}
	
	public String enableRescueMode(HetznerCloud cloud, int[] sshKeys)
	{
		return cloud.enableServerRescueMode(id, sshKeys).getRootPassword();
	}
	
	public String enableRescueMode(HetznerCloud cloud, String osType, int[] sshKeys)
	{
		return cloud.enableServerRescueMode(id, osType, sshKeys).getRootPassword();
	}
	
	public Image createImage(HetznerCloud cloud)
	{
		return cloud.createServerImage(id).getImage();
	}
	
	public Image createImage(HetznerCloud cloud, String description)
	{
		return cloud.createServerImage(id, description).getImage();
	}
	
	public Image createImage(HetznerCloud cloud, Type type)
	{
		return cloud.createServerImage(id, type).getImage();
	}
	
	public String rebuild(HetznerCloud cloud, int imageId)
	{
		return cloud.rebuildServer(id, imageId).getRootPassword();
	}
	
	public boolean changeType(HetznerCloud cloud, String type)
	{
		return cloud.changeServerType(id, type).getAction().getError() == null;
	}
	
	public boolean changeType(HetznerCloud cloud, String type, boolean upgradeDisk)
	{
		return cloud.changeServerType(id, type, upgradeDisk).getAction().getError() == null;
	}
	
	public boolean enableBackup(HetznerCloud cloud)
	{
		return cloud.enableBackup(id).getAction().getError() == null;
	}
	
	public boolean enableBackup(HetznerCloud cloud, String backupWindow)
	{
		return cloud.enableBackup(id, backupWindow).getAction().getError() == null;
	}
	
	public boolean disableBackup(HetznerCloud cloud)
	{
		return cloud.disableBackup(id).getAction().getError() == null;
	}
	
	public boolean attachIso(HetznerCloud cloud, int isoId)
	{
		return cloud.attachIsoToServer(id, isoId).getAction().getError() == null;
	}
	
	public boolean detachIso(HetznerCloud cloud)
	{
		return cloud.detachIsoFromServer(id).getAction().getError() == null;
	}
	
	public boolean changeDnsPtr(HetznerCloud cloud, String ip, String dnsPtr)
	{
		return cloud.changeServerDnsPtr(id, ip, dnsPtr).getAction().getError() == null;
	}
	
	
	public int getId() 
	{
		return id;
	}

	public String getName() 
	{
		return name;
	}

	public Status getStatus() 
	{
		return status;
	}

	public Timestamp getCreated() 
	{
		return created;
	}
	
	public Network getNetwork() 
	{
		return network;
	}

	public ServerType getServerType()
	{
		return serverType;
	}

	public Datacenter getDatacenter()
	{
		return datacenter;
	}

	public Image getImage() 
	{
		return image;
	}

	public Iso getIso() 
	{
		return iso;
	}

	public boolean isRescueEnabled()
	{
		return rescueEnabled;
	}

	public boolean isLocked()
	{
		return locked;
	}

	public String getBackupWindow()
	{
		return backupWindow;
	}

	public long getOutgoingTraffic()
	{
		return outgoingTraffic;
	}

	public long getIngoingTraffic()
	{
		return ingoingTraffic;
	}

	public long getIncludedTraffic() 
	{
		return includedTraffic;
	}

	public static enum Status
	{
		running, initializing, starting, stopping, off, deleting, migrating, rebuilding, unknown;
	}
}