package de.katzen48.hetznercloudjava.resources;

public class RequestError 
{
	private String code;
	private String message;
	
	/**
	 * Get the ErrorCode
	 * @return ErrorCode
	 */
	public String getCode() 
	{
		return code;
	}
	
	/**
	 * Get the ErrorMessage
	 * @return ErrorMessage
	 */
	public String getMessage() 
	{
		return message;
	}
}
