/*
 * $Id: SignupSubmit.java,v 1.3 2002/06/06 12:23:53 lhoriman Exp $
 * $Source: /cvsroot/mav/maverick/examples/friendbook-jsp/javasrc/org/infohazard/friendbook/ctl/SignupSubmit.java,v $
 */

package org.tagonist.friendbook.action;

import java.util.Collection;

import org.tagonist.friendbook.data.Friend;
import org.tagonist.friendbook.data.FriendBook;


/**
 * This action gets a list of friends and sets it as the model.
 */
public class GetFriends extends AuthRequired
{
	/**
	 */
	@Override
	public void authExecute() throws Exception
	{
		Collection<Friend> friends = FriendBook.getBook().findAll();

		this.getCtx().setModel(friends);
	}
}