package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class Ipv6Network 
{
	private String ip;
	private boolean blocked;
	@SerializedName("dns_ptr")
	private DNSPtr[] dnsPtr;
	
	
	/**
	 * Get the IP
	 * @return IP
	 */
	public String getIp() 
	{
		return ip;
	}
	
	/**
	 * If the IPv6 is blocked
	 * @return If the IPv6 is blocked
	 */
	public boolean isBlocked() 
	{
		return blocked;
	}
	
	/**
	 * Get the ReverseRecord
	 * Returns null, if no ReverseRecord was set
	 * @return ReverseRecord
	 */
	public DNSPtr[] getDnsPtr() 
	{
		return dnsPtr;
	}
}
