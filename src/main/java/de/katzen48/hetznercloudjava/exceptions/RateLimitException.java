package de.katzen48.hetznercloudjava.exceptions;

public class RateLimitException extends APIException
{	
	private static final long serialVersionUID = 6310973161702726792L;
	
	private long resetAfter;
	
	
	public RateLimitException(long resetAfter) 
	{
		this.resetAfter = resetAfter;
	}
	
	
	public long getResetAfter()
	{
		return resetAfter;
	}
	
	@Override
	public String getMessage() 
	{
		return "You got RateLimited. Retry after " + ((resetAfter - System.currentTimeMillis()) * 1000) + " seconds.";
	}
}
