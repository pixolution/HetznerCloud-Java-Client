package de.katzen48.hetznercloudjava.requests;

import com.google.gson.annotations.SerializedName;

public class EnableServerBackupRequest 
{
	@SerializedName("backup_window")
	private String backupWindow;
	
	
	private EnableServerBackupRequest(String backupWindow)
	{
		this.backupWindow = backupWindow;
	}
	
	
	public String getBackupWindow()
	{
		return backupWindow;
	}
	
	
	public static class Builder
	{
		private String backupWindow;
		
		
		public Builder withBackupWindow(String backupWindow)
		{
			this.backupWindow = backupWindow;
			return this;
		}
		
		public EnableServerBackupRequest build()
		{
			return new EnableServerBackupRequest(backupWindow);
		}
	}
}
