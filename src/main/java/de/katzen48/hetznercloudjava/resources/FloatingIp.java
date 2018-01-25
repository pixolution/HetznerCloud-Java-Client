package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

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
