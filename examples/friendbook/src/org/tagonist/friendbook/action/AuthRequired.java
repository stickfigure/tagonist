/*
 * $Id: SignupSubmit.java,v 1.3 2002/06/06 12:23:53 lhoriman Exp $
 * $Source: /cvsroot/mav/maverick/examples/friendbook-jsp/javasrc/org/infohazard/friendbook/ctl/SignupSubmit.java,v $
 */

package org.tagonist.friendbook.action;

import org.tagonist.ForwardException;
import org.tagonist.friendbook.data.Friend;
import org.tagonist.friendbook.data.FriendBook;

/**
 * Requires the user to be logged in.  Can be placed on a page by itself
 * or can be extended to require auth for other actions.
 */
public class AuthRequired extends FriendbookAction
{
	/** */
	public static final String LOGIN_REQUIRED_PAGE = "/login_required.jsp";
	
	/** */
	public static final String DESTINATION_ATTR = "dest";
	
	/**
	 */
	public final void execute() throws Exception
	{
		if (this.isLoggedIn())
			this.authExecute();
		else
		{
			this.getCtx().getRequest().setAttribute(DESTINATION_ATTR, this.getFullRequestURI());
			throw new ForwardException(LOGIN_REQUIRED_PAGE);
		}
	}
	
	/**
	 */
	public void authExecute() throws Exception
	{
		// By default do nothing.
	}
	
	/**
	 * @return the Friend object associated with the currently logged in user.
	 */
	public Friend getMe()
	{
		return FriendBook.getBook().findByLogin(this.currentLoginName());
	}
}