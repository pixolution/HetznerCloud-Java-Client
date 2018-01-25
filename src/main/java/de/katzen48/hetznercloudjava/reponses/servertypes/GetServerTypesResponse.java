package de.katzen48.hetznercloudjava.reponses.servertypes;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.resources.ServerType;

public class GetServerTypesResponse 
{
	@SerializedName("server_types")
	private ServerType[] serverTypes;
	
	public ServerType[] getServerTypes()
	{
		return serverTypes;
	}
}
