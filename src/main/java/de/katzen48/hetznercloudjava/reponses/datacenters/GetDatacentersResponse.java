package de.katzen48.hetznercloudjava.reponses.datacenters;

import de.katzen48.hetznercloudjava.resources.Datacenter;

public class GetDatacentersResponse 
{
	private Datacenter[] datacenters;
	private int recommendation;
	
	
	/**
	 * Get all Datacenters
	 * @return Array of Datacenters
	 */
	public Datacenter[] getDatacenters()
	{
		return datacenters;
	}
	
	/**
	 * Get recommended Datacenter
	 * @return ID of the Datacenter
	 */
	public int getRecommendation() 
	{
		return recommendation;
	}
}
