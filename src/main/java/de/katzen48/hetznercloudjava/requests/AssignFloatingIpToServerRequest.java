package de.katzen48.hetznercloudjava.requests;

public class AssignFloatingIpToServerRequest 
{
	private int server;
	
	
	public AssignFloatingIpToServerRequest(int serverId)
	{
		server = serverId;
	}
	
	
	public int getServer()
	{
		return server;
	}
}
