package de.katzen48.hetznercloudjava.reponses.server;

import de.katzen48.hetznercloudjava.resources.RequestError;
import de.katzen48.hetznercloudjava.resources.ServerAction;

public class DeleteServerResponse 
{
	private ServerAction action;
	private RequestError error;
	
	public ServerAction getAction() 
	{
		return action;
	}
	public RequestError getError() 
	{
		return error;
	}
}
