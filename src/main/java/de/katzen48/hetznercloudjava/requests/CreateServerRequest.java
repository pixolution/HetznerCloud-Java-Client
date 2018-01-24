package de.katzen48.hetznercloudjava.requests;

import com.google.gson.annotations.SerializedName;

public class CreateServerRequest 
{
	private String name;
	@SerializedName("server_type")
	private String serverType;
	@SerializedName("start_after_create")
	private boolean startAfterCreate;
	private String image;
	@SerializedName("ssh_keys")
	private int[] sshKeys;
	@SerializedName("user_data")
	private String userData;
	
	public String getName() 
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getServerType() 
	{
		return serverType;
	}
	public void setServerType(String serverType)
	{
		this.serverType = serverType;
	}
	public boolean isStartAfterCreate()
	{
		return startAfterCreate;
	}
	public void setStartAfterCreate(boolean startAfterCreate)
	{
		this.startAfterCreate = startAfterCreate;
	}
	public String getImage()
	{
		return image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}
	public int[] getSshKeys() 
	{
		return sshKeys;
	}
	public void setSshKeys(int[] sshKeys)
	{
		this.sshKeys = sshKeys;
	}
	public String getUserData() 
	{
		return userData;
	}
	public void setUserData(String userData)
	{
		this.userData = userData;
	}
}
