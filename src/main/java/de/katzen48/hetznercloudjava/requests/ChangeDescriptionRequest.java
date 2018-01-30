package de.katzen48.hetznercloudjava.requests;

public class ChangeDescriptionRequest 
{
	private String description;
	
	
	public ChangeDescriptionRequest(String description)
	{
		this.description = description;
	}
	
	
	public String getDescription()
	{
		return description;
	}
}
