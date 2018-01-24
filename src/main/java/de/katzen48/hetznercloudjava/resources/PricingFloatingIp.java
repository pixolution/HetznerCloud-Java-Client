package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class PricingFloatingIp 
{
	@SerializedName("price_monthly")
	private PriceMonthly priceMonthly;

	public PriceMonthly getPriceMonthly() 
	{
		return priceMonthly;
	}
}
