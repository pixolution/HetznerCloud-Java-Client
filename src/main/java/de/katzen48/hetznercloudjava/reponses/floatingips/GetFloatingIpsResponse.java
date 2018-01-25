package de.katzen48.hetznercloudjava.reponses.floatingips;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.resources.FloatingIp;

public class GetFloatingIpsResponse 
{
	@SerializedName("floating_ips")
	private FloatingIp[] floatingIps;
	
	public FloatingIp[] getFloatingIps()
	{
		return floatingIps;
	}
}
