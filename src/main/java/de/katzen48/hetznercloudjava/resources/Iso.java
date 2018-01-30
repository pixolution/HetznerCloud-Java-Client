package de.katzen48.hetznercloudjava.resources;

public class Iso 
{
	private int id;
	private String name;
	private String description;
	private String type;
	
	
	/**
	 * Get the ID
	 * @return ID
	 */
	public int getId() 
	{
		return id;
	}
	
	/**
	 * Get the Name of this Iso
	 * @return Name
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * Get the Description of this Iso
	 * Returns null, if no Description was set
	 * @return Description
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Get the Type of this Iso
	 * @return Type
	 */
	public String getType() 
	{
		return type;
	}
}
