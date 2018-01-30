package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class Network 
{
	private Ipv4Network ipv4;
	private Ipv6Network ipv6;
	@SerializedName("floating_ips")
	private int[] floatingIps;
	
	
	/**
	 * Get the IPv4
	 * @return IPv4
	 */
	public Ipv4Network getIpv4() 
	{
		return ipv4;
	}
	
	/**
	 * Get the IPv6
	 * @return IPv6
	 */
	public Ipv6Network getIpv6() 
	{
		return ipv6;
	}
	
	/**
	 * Get all FloatingIps
	 * @return Array of IDs
	 */
	public int[] getFloatingIps()
	{
		return floatingIps;
	}
}
