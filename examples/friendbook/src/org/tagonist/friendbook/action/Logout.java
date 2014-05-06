/*
 */

package com.voodoodyne.tagonist.friendbook.action;


/**
 * Logs self out.
 * 
 * @author Jeff Schnitzer
 */
public class Logout extends FriendbookAction 
{
	/** */
	public void execute() throws Exception
	{
		this.logout();
	}
}
