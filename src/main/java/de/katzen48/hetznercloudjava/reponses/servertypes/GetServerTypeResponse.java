package de.katzen48.hetznercloudjava.reponses.servertypes;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.resources.ServerType;

public class GetServerTypeResponse 
{
	@SerializedName("server_type")
	private ServerType serverType;
	
	public ServerType getServerType()
	{
		return serverType;
	}
}
