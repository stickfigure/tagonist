/*
 * $Id: SignupSubmit.java,v 1.3 2002/06/06 12:23:53 lhoriman Exp $
 * $Source: /cvsroot/mav/maverick/examples/friendbook-jsp/javasrc/org/infohazard/friendbook/ctl/SignupSubmit.java,v $
 */

package org.tagonist.friendbook.action;

import org.tagonist.friendbook.data.Friend;
import org.tagonist.friendbook.data.FriendBook;
import org.tagonist.propertize.Property;

/**
 * The action for the submission of the signup form.  Since the error
 * case simply sends back to the signup view, we provide input values to
 * the form as well.
 * 
 * This action also demonstrates the use of the Propertizer.
 * Propertize is an ant task that uses bytecode manipulation to
 * add getter and setter methods for fields annotated as properties.
 * Tired of repetitive typing?
 */
public class Signup extends FriendbookAction
{
	/** */
	public static class Model extends ErrorMapModel
	{
		/** */
		@Property String name = "";
		@Property String password = "";
		@Property String passwordAgain = "";
	}
	
	/** */
	public void initialize()
	{
		this.getCtx().setModel(new Model());
	}

	/**
	 */
	public void execute() throws Exception
	{
		Model model = (Model)this.getCtx().getModel();
		
		// Validate the password
		if (model.password.length() < 3)
		{
			model.setError("password", "Passwords must be at least 3 characters");
			model.password = "";
			model.passwordAgain = "";
		}
		else if (!model.password.equals(model.passwordAgain))
		{
			model.setError("password", "Your passwords did not match");
			model.password = "";
			model.passwordAgain = "";
		}

		// Validate the name
		FriendBook book = FriendBook.getBook();

		if (model.name.length() == 0)
		{
			model.setError("name", "You must choose a login name!");
		}
		else if (book.findByLogin(model.name) != null)
		{
			model.setError("name", "Someone already has that name");
		}

		// Execute if appropriate
		if (model.getErrors().isEmpty())
		{
			Friend f = new Friend();
			f.setLogin(model.name);
			f.setPassword(model.password);

			book.addFriend(f);

			this.login(model.name, model.password);
		}
	}
}