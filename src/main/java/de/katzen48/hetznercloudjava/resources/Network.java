package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class Network 
{
	private Ipv4Network ipv4;
	private Ipv6Network ipv6;
	@SerializedName("floating_ips")
	private int[] floatingIps;
	
	
	public Ipv4Network getIpv4() 
	{
		return ipv4;
	}
	public Ipv6Network getIpv6() 
	{
		return ipv6;
	}
	public int[] getFloatingIps()
	{
		return floatingIps;
	}
}
