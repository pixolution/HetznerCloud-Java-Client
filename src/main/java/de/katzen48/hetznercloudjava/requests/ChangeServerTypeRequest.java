package de.katzen48.hetznercloudjava.requests;

import com.google.gson.annotations.SerializedName;

public class ChangeServerTypeRequest 
{
	@SerializedName("server_type")
	private String type;
	@SerializedName("upgrade_disk")
	private boolean upgradeDisk;
	
	
	private ChangeServerTypeRequest(String type, boolean upgradeDisk)
	{
		this.type = type;
		this.upgradeDisk = upgradeDisk;
	}


	public String getType() 
	{
		return type;
	}

	public boolean getDescription() 
	{
		return upgradeDisk;
	}
	
	
	public static class Builder
	{
		private String type;
		private boolean upgradeDisk = false;
		
		
		public Builder withType(String type)
		{
			this.type = type;
			return this;
		}
		
		public Builder upgradeDisk(boolean upgradeDisk)
		{
			this.upgradeDisk = upgradeDisk;
			return this;
		}
		
		public ChangeServerTypeRequest build()
		{
			if(type == null)
				throw new IllegalArgumentException("Cannot build ChangeServerTypeRequest. Type cannot be null.");
			
			return new ChangeServerTypeRequest(type, upgradeDisk);
		}
	}
}
