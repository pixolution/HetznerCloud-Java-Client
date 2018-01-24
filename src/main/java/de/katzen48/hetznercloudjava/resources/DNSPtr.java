package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class DNSPtr 
{
	private String ip;
	@SerializedName("dns_ptr")
	private String dnsPtr;
	
	
	public String getIp() 
	{
		return ip;
	}
	public String getDnsPtr() 
	{
		return dnsPtr;
	}
}
