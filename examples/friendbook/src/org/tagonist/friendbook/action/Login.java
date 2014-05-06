/*
 */

package com.voodoodyne.tagonist.friendbook.action;


/**
 * Performs a login.
 * 
 * @author Jeff Schnitzer
 */
public class Login extends FriendbookAction 
{
	/** */
	public static class Model extends ErrorMapModel
	{
		/** */
		String name = "";
		public String getName() { return this.name; }
		public void setName(String value) { this.name = value; }
	
		/** */
		String password = "";
		public String getPassword() { return this.password; }
		public void setPassword(String value) { this.password = value; }
	
		/** */
		String dest = "";
		public String getDest() { return this.dest; }
		public void setDest(String value) { this.dest = value; }
	}
	
	/** Ensure that the correct model class gets populated */
	public void initialize()
	{
		this.getCtx().setModel(new Model());
	}
	
	/** */
	public void execute() throws Exception
	{
		Model model = (Model)this.getCtx().getModel();
		
		if (!this.login(model.getName(), model.getPassword()))
		{
			model.setError("name", "Invalid username or password");
			
			// Make sure we pass along any intended destination
			this.getCtx().getRequest().setAttribute(AuthRequired.DESTINATION_ATTR, model.getDest());
		}
	}
}
