package de.katzen48.hetznercloudjava.reponses.floatingips.actions;

import de.katzen48.hetznercloudjava.resources.ApiAction;
import de.katzen48.hetznercloudjava.resources.RequestError;

public class GetFloatingIpActionResponse 
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
