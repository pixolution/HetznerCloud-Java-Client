package de.katzen48.hetznercloudjava.reponses.server.actions;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.resources.ServerAction;

public class RebuildServerResponse 
{
	private ServerAction action;
	@SerializedName("root_password")
	private String rootPassword;
	
	public ServerAction getAction() 
	{
		return action;
	}
	public String getRootPassword() 
	{
		return rootPassword;
	}
}
