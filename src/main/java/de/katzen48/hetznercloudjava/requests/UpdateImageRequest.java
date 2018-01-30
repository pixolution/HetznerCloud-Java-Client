package de.katzen48.hetznercloudjava.requests;

import de.katzen48.hetznercloudjava.resources.Image.Type;

public class UpdateImageRequest 
{
	private String description;
	private Type type;
	
	
	private UpdateImageRequest(String description, Type type)
	{
		this.description = description;
		this.type = type;
	}


	public String getDescription()
	{
		return description;
	}

	public Type getType() 
	{
		return type;
	}
	
	
	public static class Builder
	{
		private String description;
		private Type type;
		
		
		public Builder withDescription(String description)
		{
			this.description = description;
			return this;
		}
		
		public Builder withType(Type type)
		{
			this.type = type;
			return this;
		}
		
		public UpdateImageRequest build()
		{
			return new UpdateImageRequest(description, type);
		}
	}
}
