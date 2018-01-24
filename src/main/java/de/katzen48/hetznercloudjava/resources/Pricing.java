package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class Pricing 
{
	private String currency;
	private float taxRate;
	private PricingImage image;
	@SerializedName("floating_ip")
	private PricingFloatingIp floatingIp;
	private PricingTraffic traffic;
	@SerializedName("server_backup")
	private PricingBackup backup;
	@SerializedName("server_types")
	private ServerType[] serverTypes;
	
	public String getCurrency() 
	{
		return currency;
	}
	public float getTaxRate() 
	{
		return taxRate;
	}
	public PricingImage getImage() 
	{
		return image;
	}
	public PricingFloatingIp getFloatingIp()
	{
		return floatingIp;
	}
	public PricingTraffic getTraffic()
	{
		return traffic;
	}
	public PricingBackup getBackup() 
	{
		return backup;
	}
	public ServerType[] getServerTypes()
	{
		return serverTypes;
	}
}
