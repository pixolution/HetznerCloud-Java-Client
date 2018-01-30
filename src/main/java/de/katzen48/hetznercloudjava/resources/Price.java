package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class Price 
{
	private String location;
	@SerializedName("price_hourly")
	private PriceHourly priceHourly;
	@SerializedName("price_monthly")
	private PriceMonthly priceMonthly;
	
	/**
	 * Get the Location
	 * @return Location
	 */
	public String getLocation() 
	{
		return location;
	}
	
	/**
	 * Get the Price per Hour
	 * @return Price per Hour
	 */
	public PriceHourly getPriceHourly() 
	{
		return priceHourly;
	}
	
	/**
	 * Get the Price per Month
	 * @return Price per Month
	 */
	public PriceMonthly getPriceMonthly() 
	{
		return priceMonthly;
	}
}
