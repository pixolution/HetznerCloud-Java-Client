package de.katzen48.hetznercloudjava.reponses.server.actions;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.resources.ApiAction;

public class RebuildServerResponse 
{
	private ApiAction action;
	@SerializedName("root_password")
	private String rootPassword;
	
	public ApiAction getAction() 
	{
		return action;
	}
	public String getRootPassword() 
	{
		return rootPassword;
	}
}
