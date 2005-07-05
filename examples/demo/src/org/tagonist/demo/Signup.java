/*
 */

package org.tagonist.demo;

import org.tagonist.AbstractAction;

/**
 * Form processing for a simple signup operation.
 * 
 * @author Jeff Schnitzer
 */
public class Signup extends AbstractAction 
{
	/** */
	String email;
	public String getEmail() { return this.email; }
	public void setEmail(String value) { this.email = value; }
	
	/** */
	String error;
	public String getError() { return this.error; }
	
	/** */
	public void execute() throws Exception
	{
		if (this.email == null || this.email.indexOf('@') < 0)
			this.error = "That is not a valid email address";
		else
			this.signupPerson(this.email);
	}
	
	/** */
	protected void signupPerson(String email)
	{
		// Insert into db or whatever
	}
}
