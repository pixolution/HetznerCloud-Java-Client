package de.katzen48.hetznercloudjava.requests;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.resources.FloatingIp.Type;

public class CreateFloatingIpRequest
{
	private Type type;
	@SerializedName("home_location")
	private String location;
	private String server;
	private String description;
	
	
	private CreateFloatingIpRequest(Type type, String location, int server, String description)
	{
		this.type = type;
		
		if(location != null)
			this.location = location;
		if(server != -1)
			this.server = Integer.toString(server);
		if(description != null)
			this.description = description;
	}


	public Type getType() {
		return type;
	}

	public String getLocation() {
		return location;
	}

	public String getServer() {
		return server;
	}

	public String getDescription() {
		return description;
	}
	
	
	public static class Builder
	{
		private Type type;
		private String location;
		private int server;
		private String description;
		
		
		public Builder withType(Type type)
		{
			this.type = type;
			return this;
		}
		
		public Builder withLocation(String location)
		{
			this.location = location;
			return this;
		}
		
		public Builder withServer(int server)
		{
			this.server = server;
			return this;
		}
		
		public Builder withDescription(String description)
		{
			this.description = description;
			return this;
		}
		
		public CreateFloatingIpRequest build()
		{
			if(type == null)
				throw new IllegalArgumentException("Cannot build CreateFloatingIpRequest. Type cannot be null.");
			
			return new CreateFloatingIpRequest(type, location, server, description);
		}
	}
}
