package de.katzen48.hetznercloudjava.requests;

import com.google.gson.annotations.SerializedName;

public class ChangeDnsPtrRequest 
{
	private String ip;
	@SerializedName("dns_ptr")
	private String dnsPtr;
	
	
	private ChangeDnsPtrRequest(String ip, String dnsPtr)
	{
		this.ip = ip;
		this.dnsPtr = dnsPtr;
	}
	
	
	public String getIp()
	{
		return ip;
	}
	
	public String getDnsPtr()
	{
		return dnsPtr;
	}
	
	
	public static class Builder
	{
		private String ip = null;
		private String dnsPtr = null;
		
		
		public Builder withIp(String ip)
		{
			this.ip = ip;
			return this;
		}
		
		public Builder withDnsPtr(String dnsPtr)
		{
			this.dnsPtr = dnsPtr;
			return this;
		}
		
		public ChangeDnsPtrRequest build()
		{
			if(ip == null)
				throw new IllegalArgumentException("Cannot build ChangeFloatingIpDnsRequest. Ip cannot be null.");
			
			return new ChangeDnsPtrRequest(ip, dnsPtr);
		}
	}
}
