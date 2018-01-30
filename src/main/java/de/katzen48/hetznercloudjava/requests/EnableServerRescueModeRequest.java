package de.katzen48.hetznercloudjava.requests;

import com.google.gson.annotations.SerializedName;

public class EnableServerRescueModeRequest 
{
	private String type;
	@SerializedName("ssh_keys")
	private int[] sshKeys;
	
	
	private EnableServerRescueModeRequest(String osType, int[] sshKeys)
	{
		this.type = osType;
		this.sshKeys = sshKeys;
	}


	public String getType() 
	{
		return type;
	}

	public int[] getSshKeys() 
	{
		return sshKeys;
	}
	
	
	public static class Builder
	{
		private String osType;
		private int[] sshKeys = new int[0];
		
		
		public Builder withOsType(String osType)
		{
			this.osType = osType;
			return this;
		}
		
		public Builder withSshKeys(int[] sshKeys)
		{
			this.sshKeys = sshKeys;
			return this;
		}
		
		public EnableServerRescueModeRequest build()
		{
			return new EnableServerRescueModeRequest(osType, sshKeys);
		}
	}
}
