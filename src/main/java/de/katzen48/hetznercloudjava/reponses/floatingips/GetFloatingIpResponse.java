package de.katzen48.hetznercloudjava.reponses.floatingips;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.resources.FloatingIp;

public class GetFloatingIpResponse 
{
	@SerializedName("floating_ips")
	private FloatingIp floatingIp;
	
	public FloatingIp getFloatingIp()
	{
		return floatingIp;
	}
}
