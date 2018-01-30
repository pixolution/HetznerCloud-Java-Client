package de.katzen48.hetznercloudjava.requests;

public class ChangeNameRequest 
{
	private String name;
	
	
	public ChangeNameRequest(String name)
	{
		this.name = name;
	}
	
	
	public String getName()
	{
		return name;
	}
}
