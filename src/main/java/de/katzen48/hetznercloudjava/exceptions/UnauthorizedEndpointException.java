package de.katzen48.hetznercloudjava.exceptions;

public class UnauthorizedEndpointException extends APIException 
{
	private static final long serialVersionUID = 7205015918913570132L;

	private String endpoint;
	
	public UnauthorizedEndpointException(String endpoint)
	{
		this.endpoint = endpoint;
	}
	
	
	public String getEndpoint()
	{
		return endpoint;
	}
	
	@Override
	public String getMessage()
	{
		return "Unauthorized Endpoint called: " + endpoint;
	}
}
