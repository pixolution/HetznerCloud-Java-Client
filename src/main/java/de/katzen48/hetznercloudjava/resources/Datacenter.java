package de.katzen48.hetznercloudjava.resources;

import com.google.gson.annotations.SerializedName;

public class Datacenter 
{
	private int id;
	private String name;
	private String description;
	private DatacenterLocation location;
	@SerializedName("server_types")
	private ServerTypes serverTypes;
	private int recommendation;
	
	
	public int getId()
	{
		return id;
	}


	public String getName() 
	{
		return name;
	}


	public String getDescription() 
	{
		return description;
	}


	public DatacenterLocation getLocation() 
	{
		return location;
	}


	public ServerTypes getServerTypes()
	{
		return serverTypes;
	}


	public int getRecommendation() 
	{
		return recommendation;
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
