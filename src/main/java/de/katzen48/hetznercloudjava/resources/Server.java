package de.katzen48.hetznercloudjava.resources;

import java.sql.Timestamp;

import com.google.gson.annotations.SerializedName;

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