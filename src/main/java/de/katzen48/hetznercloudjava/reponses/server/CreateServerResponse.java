package de.katzen48.hetznercloudjava.reponses.server;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.resources.Server;
import de.katzen48.hetznercloudjava.resources.ApiAction;

public class CreateServerResponse 
{
	private Server server;
	private ApiAction action;
	@SerializedName("root_password")
	private String rootPassword;
	
	public Server getServer() 
	{
		return server;
	}
	public ApiAction getAction()
	{
		return action;
	}
	public String getRootPassword() 
	{
		return rootPassword;
	}
}
