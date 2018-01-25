package de.katzen48.hetznercloudjava.reponses.server;

import de.katzen48.hetznercloudjava.resources.RequestError;
import de.katzen48.hetznercloudjava.resources.ApiAction;

public class DeleteServerResponse 
{
	private ApiAction action;
	private RequestError error;
	
	public ApiAction getAction() 
	{
		return action;
	}
	public RequestError getError() 
	{
		return error;
	}
}
