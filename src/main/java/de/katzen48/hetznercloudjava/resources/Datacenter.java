package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class Datacenter 
{
	private int id;
	private String name;
	private String description;
	private Location location;
	@SerializedName("server_types")
	private ServerTypes serverTypes;
	
	
	/**
	 * Get the ID of this Datacenter
	 * @return ID
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Get the Name of this Datacenter
	 * @return Name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * Get the Description of this Datacenter
	 * @return
	 */
	public String getDescription() 
	{
		return description;
	}

	/**
	 * Get the Location of this Datacenter
	 * @return Location
	 */
	public Location getLocation() 
	{
		return location;
	}

	/**
	 * Get all available ServerTypes
	 * @return Array of ServerTypes
	 */
	public ServerTypes getServerTypes()
	{
		return serverTypes;
	}


	public static class ServerTypes
	{
		private int[] supported;
		private int[] available;
		
		public int[] getSupported()
		{
			return supported;
		}
		
		public int[] getAvailable()
		{
			return available;
		}
	}
}
