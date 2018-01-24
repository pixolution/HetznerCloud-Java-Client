package de.katzen48.hetznercloudjava.reponses.server;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.actions.ServerAction;
import de.katzen48.hetznercloudjava.resources.Server;

public class CreateServerResponse 
{
	private Server server;
	private ServerAction action;
	@SerializedName("root_password")
	private String rootPassword;
	
	public Server getServer() 
	{
		return server;
	}
	public ServerAction getAction()
	{
		return action;
	}
	public String getRootPassword() 
	{
		return rootPassword;
	}
}
