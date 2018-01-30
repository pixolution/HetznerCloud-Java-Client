package de.katzen48.hetznercloudjava.requests;

import de.katzen48.hetznercloudjava.resources.Image.Type;

public class CreateServerImageRequest 
{
	private Type type;
	private String description;
	
	
	private CreateServerImageRequest(Type type, String description)
	{
		this.type = type;
		this.description = description;
	}


	public Type getType() 
	{
		return type;
	}

	public String getDescription() 
	{
		return description;
	}
	
	
	public static class Builder
	{
		private Type type;
		private String description;
		
		
		public Builder withType(Type type)
		{
			this.type = type;
			return this;
		}
		
		public Builder withDescription(String description)
		{
			this.description = description;
			return this;
		}
		
		public CreateServerImageRequest build()
		{
			return new CreateServerImageRequest(type, description);
		}
	}
}
