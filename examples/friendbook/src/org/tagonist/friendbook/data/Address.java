/*
 */

package com.voodoodyne.tagonist.friendbook.data;

/**
 * Address entry.
 */
public class Address
{
	/**
	 */
	protected String line1;
	protected String line2;
	protected String city;
	protected String state;
	protected String description="Home";
	
	/**
	 */
	public void setAddressLine1(String ln) { this.line1 = ln; }
	public String getAddressLine1() { return this.line1; }
	
	/**
	 */
	public void setAddressLine2(String ln) { this.line2 = ln; }
	public String getAddressLine2() { return this.line2; }

	/**
	 */
	public void setCity(String city) { this.city = city; }
	public String getCity() { return this.city; }

	/**
	 */
	public void setState(String state) { this.state = state; }
	public String getState() { return this.state; }
	
	/**
	 */
	public void setDescription(String description) { this.description = description; }
	public String getDescription() { return this.description; }
	
}
