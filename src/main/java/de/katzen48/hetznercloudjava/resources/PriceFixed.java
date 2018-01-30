package de.katzen48.hetznercloudjava.resources;

abstract class PriceFixed 
{
	private float net;
	private float gross;
	
	/**
	 * Get the Price before Tax
	 * @return Price before Tax
	 */
	public float getNet() 
	{
		return net;
	}
	
	/**
	 * Get the Price with Tax
	 * @return Price with Tax
	 */
	public float getGross() 
	{
		return gross;
	}
}
