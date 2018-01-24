package de.katzen48.hetznercloudjava.reponses.server.actions;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.actions.ServerAction;

public class ResetPasswordResponse 
{
	@SerializedName("root_password")
	private String rootPassword;
	private ServerAction action;
	
	public String getRootPassword() 
	{
		return rootPassword;
	}
	public ServerAction getAction()
	{
		return action;
	}
}
