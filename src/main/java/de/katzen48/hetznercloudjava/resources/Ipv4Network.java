package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class Ipv4Network 
{
	private String ip;
	private boolean blocked;
	@SerializedName("dns_ptr")
	private String dnsPtr;
	
	
	public String getIp()
	{
		return ip;
	}
	public boolean isBlocked()
	{
		return blocked;
	}
	public String getDnsPtr() 
	{
		return dnsPtr;
	}
	
	
}
