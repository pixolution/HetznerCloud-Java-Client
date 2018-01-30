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
	
	
	/**
	 * Change the Description of this FloatingIP
	 * @param cloud Provided Cloud
	 * @param description Preferred Description
	 * @return If the Description was changed
	 */
	public boolean changeDescription(HetznerCloud cloud, String description)
	{
		if(cloud.changeFloatingIpDescription(id, description).description.equals(this.description))
		{
			this.description = description;
			return true;
		}
		return false;
	}
	
	/**
	 * Delete this FloatingIP
	 * @param cloud Provided Cloud
	 * @return If this FloatingIP was deleted
	 */
	public boolean delete(HetznerCloud cloud)
	{
		return cloud.deleteFloatingIp(id);
	}
	
	/**
	 * Get all ApiActions
	 * @param cloud Provided Cloud
	 * @return Array of ApiActions
	 */
	public ApiAction[] getActions(HetznerCloud cloud)
	{
		return cloud.getFloatingApiActions(id);
	}
	
	/**
	 * Assign this FloatingIP to a Server
	 * @param cloud Provided Cloud
	 * @param serverId Preferred Server
	 * @return If this FloatingIP was assigned to the Server
	 */
	public boolean assign(HetznerCloud cloud, int serverId)
	{
		return cloud.assignFloatingIpToServer(id, serverId).getError() == null;
	}
	
	/**
	 * Unassign this FloatingIP
	 * @param cloud Provided Cloud
	 * @return If this FloatingIP was unassigned
	 */
	public boolean unassign(HetznerCloud cloud)
	{
		return cloud.unassignFloatingIp(id).getError() == null;
	}
	
	/**
	 * Change the ReverseRecord of this FloatingIP
	 * @param cloud Provided Cloud
	 * @param ip The IP
	 * @param dnsPtr The ReverseRecord
	 * @return If the ReverseRecord was added
	 */
	public boolean changeDnsPtr(HetznerCloud cloud, String ip, String dnsPtr)
	{
		return cloud.changeFloatingIpDnsPtr(id, ip, dnsPtr).getError() == null;
	}
	
	/**
	 * Get the ID of this FloatingIP
	 * @return ID
	 */
	public int getId() 
	{
		return id;
	}

	/**
	 * Get the Description of this FloatingIP
	 * @return Description
	 */
	public String getDescription() 
	{
		return description;
	}

	/**
	 * Get the IP
	 * @return IP
	 */
	public String getIp() 
	{
		return ip;
	}

	/**
	 * Get the Type
	 * @return Type
	 */
	public Type getType() 
	{
		return type;
	}

	/**
	 * Get the assigned Server
	 * Returns "-1" when not assigned
	 * @return ServerID
	 */
	public int getServer()
	{
		return server;
	}

	/**
	 * Get ReverseRecords
	 * @return Array of DNSPtr
	 */
	public DNSPtr[] getDnsPtrs()
	{
		return dnsPtrs;
	}

	/**
	 * Get Home Location
	 * @return Location
	 */
	public Location getHomeLocation()
	{
		return homeLocation;
	}

	/**
	 * If this FloatingIP is blocked
	 * @return If this FloatingIP is blocked
	 */
	public boolean isBlocked()
	{
		return blocked;
	}


	public static enum Type
	{
		ipv4, ipv6;
	}
}
