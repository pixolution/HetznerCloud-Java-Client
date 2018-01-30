package de.katzen48.hetznercloudjava.requests;

public class RebuildServerRequest 
{
	private int image;
	
	
	public RebuildServerRequest(int imageId)
	{
		this.image = imageId;
	}
	
	
	public int getImage()
	{
		return image;
	}
}
