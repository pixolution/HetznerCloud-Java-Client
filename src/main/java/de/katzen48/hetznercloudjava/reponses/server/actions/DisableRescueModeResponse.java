package de.katzen48.hetznercloudjava.reponses.server.actions;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.resources.ApiAction;

public class DisableRescueModeResponse 
{
	@SerializedName("root_password")
	private String rootPassword;
	private ApiAction action;
	
	public String getRootPassword() 
	{
		return rootPassword;
	}
	public ApiAction getAction()
	{
		return action;
	}
}
