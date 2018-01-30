package de.katzen48.hetznercloudjava.resources;

import java.sql.Timestamp;

public class ApiAction 
{
	private int id;
	private String command;
	private Status status;
	private int progress;
	private Timestamp started;
	private Timestamp finished;
	private Resource[] resources;
	private RequestError error;
	
	
	/**
	 * Get the ID of this Action
	 * @return ID
	 */
	public int getId() 
	{
		return id;
	}

	/**
	 * Get the Command
	 * @return Command
	 */
	public String getCommand() 
	{
		return command;
	}
	
	/**
	 * Get the Status
	 * @return Status
	 */
	public Status getStatus()
	{
		return status;
	}
	
	/**
	 * Get the Progress
	 * @return Progress
	 */
	public int getProgress()
	{
		return progress;
	}

	/**
	 * Get when the Action was started
	 * @return Timestamp
	 */
	public Timestamp getStarted()
	{
		return started;
	}

	/**
	 * Get when the Action was finished
	 * @return Timestamp
	 */
	public Timestamp getFinished()
	{
		return finished;
	}
	
	/**
	 * Get all Resources
	 * @return Array of Resources
	 */
	public Resource[] getResources()
	{
		return resources;
	}

	public RequestError getError() 
	{
		return error;
	}


	public static enum Status
	{
		running, success, error;
	}
}
