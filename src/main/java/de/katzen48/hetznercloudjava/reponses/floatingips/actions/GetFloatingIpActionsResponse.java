package de.katzen48.hetznercloudjava.reponses.floatingips.actions;

import de.katzen48.hetznercloudjava.resources.ApiAction;
import de.katzen48.hetznercloudjava.resources.RequestError;

public class GetFloatingIpActionsResponse 
{
	private ApiAction[] actions;
	private RequestError error;
	
	public ApiAction[] getActions()
	{
		return actions;
	}
	
	public RequestError getError()
	{
		return error;
	}
}
