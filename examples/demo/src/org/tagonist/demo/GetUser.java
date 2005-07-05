/*
 */

package org.tagonist.demo;

import org.tagonist.AbstractAction;

/**
 * Gets a hypothetical user object and makes it available as the model.
 * 
 * @author Jeff Schnitzer
 */
public class GetUser extends AbstractAction 
{
	/** */
	long id;
	public void setId(long value) { this.id = value; }
	
	/** */
	public void execute() throws Exception
	{
		User u = this.fetchUser(this.id);
		
		this.getCtx().setModel(u);
	}
	
	/**
	 * Could fetch the user info from a database, etc
	 */
	User fetchUser(long id)
	{
		if (id == 1)
			return new User(1, "Bob", "bob@subgenius.org");
		else if (id == 2)
			return new User(2, "Marvin", "marvin@siriuscybernetics.com");
		else
			return new User(0, "Joe Default", "president@whitehouse.gov");
	}
}
