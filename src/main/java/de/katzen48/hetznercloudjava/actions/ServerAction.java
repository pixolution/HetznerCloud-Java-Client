package de.katzen48.hetznercloudjava.actions;

import java.sql.Timestamp;

import de.katzen48.hetznercloudjava.resources.RequestError;
import de.katzen48.hetznercloudjava.resources.Resource;

public class ServerAction 
{
	private int id;
	private String command;
	private Status status;
	private int progress;
	private Timestamp started;
	private Timestamp finished;
	private Resource[] resources;
	private RequestError error;
	
	
	public int getId() 
	{
		return id;
	}

	public String getCommand() 
	{
		return command;
	}
	
	public Status getStatus()
	{
		return status;
	}

	public int getProgress()
	{
		return progress;
	}

	public Timestamp getStarted()
	{
		return started;
	}

	public Timestamp getFinished()
	{
		return finished;
	}

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
