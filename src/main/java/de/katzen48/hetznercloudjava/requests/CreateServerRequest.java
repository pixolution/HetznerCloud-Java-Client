package de.katzen48.hetznercloudjava.requests;

import com.google.gson.annotations.SerializedName;

import de.katzen48.hetznercloudjava.HetznerCloud;
import de.katzen48.hetznercloudjava.reponses.server.CreateServerResponse;

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
	
	
	private CreateServerRequest() {}
	
	
	public CreateServerResponse execute(HetznerCloud cloud)
	{
		return cloud.createServer(this);
	}
	
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
	
	public static class Builder
	{
		private CreateServerRequest request;
		
		public Builder()
		{
			request = new CreateServerRequest();
		}
		
		public Builder withName(String name)
		{
			request.setName(name);
			return this;
		}
		
		public Builder withType(String type)
		{
			request.setServerType(type);
			return this;
		}
		
		public Builder startAfterCreation(boolean startAfterCreation)
		{
			request.setStartAfterCreate(startAfterCreation);
			return this;
		}
		
		public Builder withImage(String image)
		{
			request.setImage(image);
			return this;
		}
		
		public Builder withSshKeys(int[] keys)
		{
			request.setSshKeys(keys);
			return this;
		}
		
		public Builder withUserData(String userData)
		{
			request.setUserData(userData);
			return this;
		}
		
		public CreateServerRequest build()
		{
			if(request.getName() == null || request.getImage() == null || request.getServerType() == null)
				throw new IllegalArgumentException("Could not build CreateServerRequest. You need to specify a name, an image and a type.");
			
			return request;
		}
	}
}
