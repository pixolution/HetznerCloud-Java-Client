package de.katzen48.hetznercloudjava.resources;

public class Location 
{
	private int id;
	private String name;
	private String country;
	private String city;
	private float latitude;
	private float longitude;
	
	
	/**
	 * Get the ID
	 * @return ID
	 */
	public int getId() 
	{
		return id;
	}
	
	/**
	 * Get the Name of this Location
	 * @return Name
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Get the CountryCode for this Location
	 * @return Country
	 */
	public String getCountry() 
	{
		return country;
	}
	
	/**
	 * Get the City for this Location
	 * @return City
	 */
	public String getCity() 
	{
		return city;
	}
	
	/**
	 * Get the Latitude for this Location
	 * @return Latitude
	 */
	public float getLatitude()
	{
		return latitude;
	}
	
	/**
	 * Get the Longitude for this Location
	 * @return Longitude
	 */
	public float getLongitude()
	{
		return longitude;
	}
}
