/*
 * $Id: FriendBook.java,v 1.1 2001/12/19 22:22:00 lhoriman Exp $
 * $Source: /cvsroot/mav/maverick/examples/friendbook-jsp/javasrc/org/infohazard/friendbook/data/FriendBook.java,v $
 */

package org.tagonist.friendbook.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Storage and retreival for friends.  Since this is a simple example,
 * no database persistance is involved.
 */
public class FriendBook
{
	/**
	 * The singleton book instance
	 */
	protected static FriendBook instance = new FriendBook();

	/**
	 * Storage for friend objects.  Maps String (login) -> Friend
	 */
	protected Map<String, Friend> friends = new HashMap<String, Friend>();

	/**
	 * Protected constructor; use FriendBook.getBook()
	 */
	protected FriendBook()
	{
		// Add some friends for entertainment purposes.
		Friend f = new Friend();

		f.setLogin("marge");
		f.setPassword("pass");
		f.setFirstName("Marge");
		f.setLastName("Simpson");

		f.getAddress().setCity("Springfield");
		f.getAddress().setState("NJ");

		f.getPhoneList().add("123-456-7890");
		f.getEmailList().add("marge@springfield.net");

		this.addFriend(f);

		//
		f = new Friend();

		f.setLogin("bob");
		f.setPassword("pass");
		f.setFirstName("Robert");
		f.setLastName("Dobbs");

		f.getAddress().setAddressLine1("717 Subgenius Way");
		f.getAddress().setCity("San Luis Obispo");
		f.getAddress().setState("CA");

		f.getPhoneList().add("805-555-1212");
		f.getPhoneList().add("415-123-4567");
		f.getEmailList().add("bob@frop.org");

		this.addFriend(f);

		//
		f = new Friend();

		f.setLogin("marvin");
		f.setPassword("pass");
		f.setFirstName("Marvin");
		f.setLastName("Robot");

		f.getAddress().setAddressLine1("Milliways");
		f.getAddress().setAddressLine2("End of the Universe");
		f.getAddress().setState("Lunchtime");

		f.getPhoneList().add("42-6x9");
		f.getEmailList().add("towel@siriuscybernetics.com");
		f.getEmailList().add("marvin@hotmail.com");

		this.addFriend(f);

		//
		f = new Friend();

		f.setLogin("john");
		f.setPassword("pass");
		f.setFirstName("John");
		f.setLastName("Uml�ut");

		f.getAddress().setAddressLine1("nowhere");
		f.getAddress().setAddressLine2("here");
		f.getAddress().setState("busy");

		f.getPhoneList().add("44-");
		f.getEmailList().add("�@hhehe.yu");

		this.addFriend(f);	}

	/**
	 * @return the singleton friendbook instance
	 */
	public static FriendBook getBook()
	{
		return instance;
	}

	/**
	 * @return a collection of all the Friend objects
	 */
	public Collection<Friend> findAll()
	{
		return this.friends.values();
	}

	/**
	 * @return null if login could not be found.
	 */
	public Friend findByLogin(String login)
	{
		return this.friends.get(login);
	}

	/**
	 * Adds the friend.  Will replace any existing friend with
	 * the same login, so be careful.
	 */
	public void addFriend(Friend f)
	{
		this.friends.put(f.getLogin(), f);
	}

	/**
	 * Removes all trace of the specified friend.
	 */
	public void removeFriend(String login)
	{
		this.friends.remove(login);
	}
}