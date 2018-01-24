package de.katzen48.hetznercloudjava.reponses.server;

import de.katzen48.hetznercloudjava.actions.ServerAction;
import de.katzen48.hetznercloudjava.resources.RequestError;

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
