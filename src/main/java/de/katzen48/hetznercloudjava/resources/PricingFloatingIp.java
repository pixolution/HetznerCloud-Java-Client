package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class PricingFloatingIp 
{
	@SerializedName("price_monthly")
	private PriceMonthly priceMonthly;

	/**
	 * Get the Price per Month
	 * @return Price per Month
	 */
	public PriceMonthly getPriceMonthly() 
	{
		return priceMonthly;
	}
}
