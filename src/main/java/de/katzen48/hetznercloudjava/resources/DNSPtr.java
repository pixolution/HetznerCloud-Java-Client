package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class DNSPtr 
{
	private String ip;
	@SerializedName("dns_ptr")
	private String dnsPtr;
	
	
	/**
	 * Get the IP
	 * @return IP
	 */
	public String getIp() 
	{
		return ip;
	}
	
	/**
	 * Get the ReverseRecord
	 * @return ReverseRecord
	 */
	public String getDnsPtr() 
	{
		return dnsPtr;
	}
}
