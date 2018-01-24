package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class PricingImage 
{
	@SerializedName("price_per_gb_month")
	private PriceMonthly pricePerGbMonth;

	public PriceMonthly getPricePerGbMonth() 
	{
		return pricePerGbMonth;
	}
}
