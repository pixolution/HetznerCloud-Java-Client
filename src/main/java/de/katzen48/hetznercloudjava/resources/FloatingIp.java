package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.HetznerCloud;

public class FloatingIp 
{
	private int id;
	private String description;
	private String ip;
	private Type type;
	private int server;
	@SerializedName("dns_ptr")
	private DNSPtr[] dnsPtrs;
	@SerializedName("home_location")
	private Location homeLocation;
	private boolean blocked;
	
	
	public boolean changeDescription(HetznerCloud cloud, String description)
	{
		if(cloud.changeFloatingIpDescription(id, description).description.equals(this.description))
		{
			this.description = description;
			return true;
		}
		return false;
	}
	
	public boolean delete(HetznerCloud cloud)
	{
		return cloud.deleteFloatingIp(id);
	}
	
	public ApiAction[] getActions(HetznerCloud cloud)
	{
		return cloud.getFloatingApiActions(id);
	}
	
	public boolean assign(HetznerCloud cloud, int serverId)
	{
		return cloud.assignFloatingIpToServer(id, serverId).getError() == null;
	}
	
	public boolean unassign(HetznerCloud cloud)
	{
		return cloud.unassignFloatingIp(id).getError() == null;
	}
	
	public boolean changeDnsPtr(HetznerCloud cloud, String ip, String dnsPtr)
	{
		return cloud.changeFloatingIpDnsPtr(id, ip, dnsPtr).getError() == null;
	}
	
	
	public int getId() 
	{
		return id;
	}

	public String getDescription() 
	{
		return description;
	}

	public String getIp() 
	{
		return ip;
	}

	public Type getType() 
	{
		return type;
	}

	public int getServer()
	{
		return server;
	}

	public DNSPtr[] getDnsPtrs()
	{
		return dnsPtrs;
	}

	public Location getHomeLocation()
	{
		return homeLocation;
	}

	public boolean isBlocked()
	{
		return blocked;
	}


	public static enum Type
	{
		ipv4, ipv6;
	}
}
