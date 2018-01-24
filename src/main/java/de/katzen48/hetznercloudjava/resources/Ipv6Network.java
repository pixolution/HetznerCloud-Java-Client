package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class Ipv6Network 
{
	private String ip;
	private boolean blocked;
	@SerializedName("dns_ptr")
	private DNSPtr[] dnsPtr;
	
	
	public String getIp() 
	{
		return ip;
	}
	public boolean isBlocked() 
	{
		return blocked;
	}
	public DNSPtr[] getDnsPtr() 
	{
		return dnsPtr;
	}
}
