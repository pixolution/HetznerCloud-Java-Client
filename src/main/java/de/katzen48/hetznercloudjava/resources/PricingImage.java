package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class PricingImage 
{
	@SerializedName("price_per_gb_month")
	private PriceMonthly pricePerGbMonth;

	/**
	 * Get the Price per GigaByte per Month
	 * @return Price per GigaByte per Month
	 */
	public PriceMonthly getPricePerGbMonth() 
	{
		return pricePerGbMonth;
	}
}
