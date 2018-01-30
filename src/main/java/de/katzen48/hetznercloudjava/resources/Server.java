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

	/**
	 * Change the Name if this Server
	 * @param cloud Provided Cloud
	 * @param name The new Name
	 * @return If the Name was changed
	 */
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
	
	/**
	 * Delete this Server
	 * @param cloud Provided Cloud
	 * @return If this Server was deleted
	 */
	public boolean delete(HetznerCloud cloud)
	{
		return cloud.deleteServer(id);
	}
	
	/**
	 * Get all Actions for this Server
	 * @param cloud Provided Cloud
	 * @return Array of ApiActions
	 */
	public ApiAction[] getActions(HetznerCloud cloud)
	{
		return cloud.getServerActions(id);
	}
	
	/**
	 * Get a specific Action
	 * @param cloud Provided Cloud
	 * @param id Preferred Action
	 * @return The ApiAction
	 */
	public ApiAction getServerAction(HetznerCloud cloud, int id)
	{
		return cloud.getServerAction(this.id, id);
	}
	
	/**
	 * Power this Server On
	 * @param cloud Provided Cloud
	 * @return If the Server was powered on
	 */
	public boolean poweron(HetznerCloud cloud)
	{
		return cloud.poweronServer(id).getError() == null;
	}
	
	/**
	 * Reboot this Server
	 * @param cloud Provided Cloud
	 * @return If the Server was rebooted
	 */
	public boolean reboot(HetznerCloud cloud)
	{
		return cloud.rebootServer(id).getError() == null;
	}
	
	/**
	 * Reset this Server
	 * @param cloud Provided Cloud
	 * @return If the Server was resetted
	 */
	public boolean reset(HetznerCloud cloud)
	{
		return cloud.resetServer(id).getError() == null;
	}

	/**
	 * Shut this Server down
	 * @param cloud Provided Cloud
	 * @return If the Server was shut down
	 */
	public boolean shutdown(HetznerCloud cloud)
	{
		return cloud.shutdownServer(id).getError() == null;
	}
	
	/**
	 * Power this Server off
	 * @param cloud Provided Cloud
	 * @return If the Server was powered off
	 */
	public boolean poweroff(HetznerCloud cloud)
	{
		return cloud.poweroffServer(id).getError() == null;
	}
	
	/**
	 * Reset this Server's Root Password
	 * @param cloud Provided Cloud
	 * @return The new Root Password
	 */
	public String resetServerPassword(HetznerCloud cloud)
	{
		return cloud.resetServerPassword(id).getRootPassword();
	}
	
	/**
	 * Enable this Server's Rescue Mode
	 * @param cloud Provided Cloud
	 * @return The Root Password
	 */
	public String enableRescueMode(HetznerCloud cloud)
	{
		return cloud.enableServerRescueMode(id).getRootPassword();
	}
	
	/**
	 * Enable this Server's Rescue Mode using a specific OsType
	 * @param cloud Provided Cloud
	 * @param osType The OsType
	 * @return The new Root Password
	 */
	public String enableRescueMode(HetznerCloud cloud, String osType)
	{
		return cloud.enableServerRescueMode(id, osType).getRootPassword();
	}
	
	/**
	 * Enable this Server's Rescue Mode and insert your SshKeys
	 * @param cloud Provided Cloud
	 * @param sshKeys Your SshKeys
	 * @return The new Root Password
	 */
	public String enableRescueMode(HetznerCloud cloud, int[] sshKeys)
	{
		return cloud.enableServerRescueMode(id, sshKeys).getRootPassword();
	}
	
	/**
	 * Enable this Server's Rescue Mode using a specific OsType and insert your SshKeys
	 * @param cloud Provided Cloud
	 * @param osType Your OsType
	 * @param sshKeys Your SshKeys
	 * @return The new Root Password
	 */
	public String enableRescueMode(HetznerCloud cloud, String osType, int[] sshKeys)
	{
		return cloud.enableServerRescueMode(id, osType, sshKeys).getRootPassword();
	}
	
	/**
	 * Create an Image of this Server
	 * @param cloud Provided Cloud
	 * @return The Image
	 */
	public Image createImage(HetznerCloud cloud)
	{
		return cloud.createServerImage(id).getImage();
	}
	
	/**
	 * Create an Image of this Server with a specific Description
	 * @param cloud Provided Cloud
	 * @param description Preferred Description
	 * @return The Image
	 */
	public Image createImage(HetznerCloud cloud, String description)
	{
		return cloud.createServerImage(id, description).getImage();
	}
	
	/**
	 * Create an Image of this Server with a specific Type
	 * @param cloud Provided Cloud
	 * @param type Preferred Description
	 * @return The Image
	 */
	public Image createImage(HetznerCloud cloud, Type type)
	{
		return cloud.createServerImage(id, type).getImage();
	}
	
	/**
	 * Create an Image of this Server with a specific Type and Description
	 * @param cloud Provided Cloud
	 * @param type Preferred Type
	 * @param description Preferred Description
	 * @return The Image
	 */
	public Image createImage(HetznerCloud cloud, Type type, String description)
	{
		return cloud.createServerImage(id, description, type).getImage();
	}
	
	/**
	 * Rebuild a Server
	 * @param cloud Provided Cloud
	 * @param imageId Preferred Image
	 * @return The new Root Password
	 */
	public String rebuild(HetznerCloud cloud, int imageId)
	{
		return cloud.rebuildServer(id, imageId).getRootPassword();
	}
	
	/**
	 * Change the Type of this Server
	 * @param cloud Provided Cloud
	 * @param type The new Type
	 * @return If the Type of this Server was changed
	 */
	public boolean changeType(HetznerCloud cloud, String type)
	{
		return cloud.changeServerType(id, type).getAction().getError() == null;
	}
	
	/**
	 * Change the Type of this Server and upgrade the Disk
	 * @param cloud Provided Cloud
	 * @param type The new Type
	 * @param upgradeDisk If the Disk should be upgraded
	 * @return If the Type of this Server was changed
	 */
	public boolean changeType(HetznerCloud cloud, String type, boolean upgradeDisk)
	{
		return cloud.changeServerType(id, type, upgradeDisk).getAction().getError() == null;
	}
	
	/**
	 * Enable Backups for this Server
	 * @param cloud Provided Cloud
	 * @return If the Backups were enabled
	 */
	public boolean enableBackup(HetznerCloud cloud)
	{
		return cloud.enableBackup(id).getAction().getError() == null;
	}
	
	/**
	 * Enable Backups for this Server with a specific Backup Window
	 * @param cloud Provided Cloud
	 * @param backupWindow Preferred Backup Window
	 * @return If the Backups were enabled
	 */
	public boolean enableBackup(HetznerCloud cloud, String backupWindow)
	{
		return cloud.enableBackup(id, backupWindow).getAction().getError() == null;
	}
	
	/**
	 * Disable Backups for this Server
	 * @param cloud Provided Cloud
	 * @return If Backups were disabled
	 */
	public boolean disableBackup(HetznerCloud cloud)
	{
		return cloud.disableBackup(id).getAction().getError() == null;
	}
	
	/**
	 * Attach an Iso to this Server
	 * @param cloud Provided Cloud
	 * @param isoId Preferred Iso
	 * @return If the Iso was attached to this Server
	 */
	public boolean attachIso(HetznerCloud cloud, int isoId)
	{
		return cloud.attachIsoToServer(id, isoId).getAction().getError() == null;
	}
	
	/**
	 * Detach an Iso from this Server
	 * @param cloud Provided Cloud
	 * @return If this Server's Iso was detached
	 */
	public boolean detachIso(HetznerCloud cloud)
	{
		return cloud.detachIsoFromServer(id).getAction().getError() == null;
	}
	
	/**
	 * Change the ReverseRecord
	 * @param cloud Provided Cloud
	 * @param ip The new IP
	 * @param dnsPtr The DnsPTR
	 * @return If the ReverseRecord was changed
	 */
	public boolean changeDnsPtr(HetznerCloud cloud, String ip, String dnsPtr)
	{
		return cloud.changeServerDnsPtr(id, ip, dnsPtr).getAction().getError() == null;
	}
	
	/**
	 * Get the ID
	 * @return ID
	 */
	public int getId() 
	{
		return id;
	}

	/**
	 * Get the Name
	 * @return Name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * Get the Status
	 * @return Status
	 */
	public Status getStatus() 
	{
		return status;
	}

	/**
	 * Get when the Server was created
	 * @return Timestamp
	 */
	public Timestamp getCreated() 
	{
		return created;
	}
	
	/**
	 * Get the Network
	 * @return Network
	 */
	public Network getNetwork() 
	{
		return network;
	}

	/**
	 * Get the ServerType
	 * @return ServerType
	 */
	public ServerType getServerType()
	{
		return serverType;
	}

	/**
	 * Get the Datacenter
	 * @return Datacenter
	 */
	public Datacenter getDatacenter()
	{
		return datacenter;
	}

	/**
	 * Get the Image this Server was created from
	 * Returns null if Server was not created from Image
	 * @return Image
	 */
	public Image getImage() 
	{
		return image;
	}

	/**
	 * Get the attached Iso
	 * Returns null if no Iso is attached to this Server
	 * @return Iso
	 */
	public Iso getIso() 
	{
		return iso;
	}

	/**
	 * If Rescue Mode is enabled
	 * @return If Rescue Mode is enabled
	 */
	public boolean isRescueEnabled()
	{
		return rescueEnabled;
	}

	/**
	 * If this Server is locked
	 * @return If this Server is locked
	 */
	public boolean isLocked()
	{
		return locked;
	}

	/**
	 * Get the Backup Window
	 * Returns null if Backups are not enabled
	 * @return Backup Window
	 */
	public String getBackupWindow()
	{
		return backupWindow;
	}

	/**
	 * Get the outgoing Traffic
	 * @return Outgoing Traffic
	 */
	public long getOutgoingTraffic()
	{
		return outgoingTraffic;
	}

	/**
	 * Get the ingoing Traffic
	 * @return Ingoing Traffic
	 */
	public long getIngoingTraffic()
	{
		return ingoingTraffic;
	}

	/**
	 * Get the free Traffic included in your plan
	 * @return Included Traffic
	 */
	public long getIncludedTraffic() 
	{
		return includedTraffic;
	}

	public static enum Status
	{
		running, initializing, starting, stopping, off, deleting, migrating, rebuilding, unknown;
	}
}