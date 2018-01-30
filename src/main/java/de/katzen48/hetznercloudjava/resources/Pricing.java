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
	
	/**
	 * Get the Currency
	 * @return Currency
	 */
	public String getCurrency() 
	{
		return currency;
	}
	
	/**
	 * Get the TaxRate
	 * @return TaxRate
	 */
	public float getTaxRate() 
	{
		return taxRate;
	}
	
	/**
	 * Get the Price for Images
	 * @return Price for Images
	 */
	public PricingImage getImage() 
	{
		return image;
	}
	
	/**
	 * Get the Price for FloatingIps
	 * @return Price for FloatingIps
	 */
	public PricingFloatingIp getFloatingIp()
	{
		return floatingIp;
	}
	
	/**
	 * Get the Price for Traffic
	 * @return Price for Traffic
	 */
	public PricingTraffic getTraffic()
	{
		return traffic;
	}
	
	/**
	 * Get the Price for Backups
	 * @return Price for Backups
	 */
	public PricingBackup getBackup() 
	{
		return backup;
	}
	
	/**
	 * Get the Price for specific ServerTypes
	 * @return Price for specific ServerTypes
	 */
	public ServerType[] getServerTypes()
	{
		return serverTypes;
	}
}
