/*
 * $Id: SignupSubmit.java,v 1.3 2002/06/06 12:23:53 lhoriman Exp $
 * $Source: /cvsroot/mav/maverick/examples/friendbook-jsp/javasrc/org/infohazard/friendbook/ctl/SignupSubmit.java,v $
 */

package org.tagonist.friendbook.action;

import org.tagonist.friendbook.data.Friend;
import org.tagonist.friendbook.data.FriendBook;

/**
 * The action for the submission of the signup form.  Since the error
 * case simply sends back to the signup view, we provide input values to
 * the form as well.
 */
public class Signup extends FriendbookAction
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
		String passwordAgain = "";
		public String getPasswordAgain() { return this.passwordAgain; }
		public void setPasswordAgain(String value) { this.passwordAgain = value; }
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
		if (model.getPassword().length() < 3)
		{
			model.setError("password", "Passwords must be at least 3 characters");
			model.setPassword("");
			model.setPasswordAgain("");
		}
		else if (!model.getPassword().equals(model.getPasswordAgain()))
		{
			model.setError("password", "Your passwords did not match");
			model.setPassword("");
			model.setPasswordAgain("");
		}

		// Validate the name
		FriendBook book = FriendBook.getBook();

		if (model.getName().length() == 0)
		{
			model.setError("name", "You must choose a login name!");
		}
		else if (book.findByLogin(model.getName()) != null)
		{
			model.setError("name", "Someone already has that name");
		}

		// Execute if appropriate
		if (model.getErrors().isEmpty())
		{
			Friend f = new Friend();
			f.setLogin(model.getName());
			f.setPassword(model.getPassword());

			book.addFriend(f);

			this.login(model.getName(), model.getPassword());
		}
	}
}