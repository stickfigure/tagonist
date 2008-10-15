/*
 */

package org.tagonist.friendbook.data;

import java.util.LinkedList;
import java.util.List;

/**
 * Data for a friend.
 */
public class Friend
{
	protected String login = "";
	protected String password = "";

	protected String firstName = "";
	protected String lastName = "";

	protected Address address = new Address();

	protected List<String> phoneList = new LinkedList<String>();
	protected List<String> emailList = new LinkedList<String>();

	/**
	 */
	public String getLogin() { return this.login; }
	public void setLogin(String login) { this.login = login; }

	/**
	 */
	public String getPassword() { return this.password; }
	public void setPassword(String password) { this.password = password; }

	/**
	 */
	public String getFirstName() { return this.firstName; }
	public void setFirstName(String first) { this.firstName = first; }

	/**
	 */
	public String getLastName() { return this.lastName; }
	public void setLastName(String last) { this.lastName = last; }

	/**
	 */
	public Address getAddress()	{ return this.address; }
	public void setAddress(Address addr) { this.address = addr; }

	/**
	 */
	public List<String> getPhoneList() { return this.phoneList; }
	public void setPhoneList(List<String> list) { this.phoneList = list; }

	/**
	 */
	public List<String> getEmailList() { return this.emailList; }
	public void setEmailList(List<String> list) { this.emailList = list; }

	/**
	 */
	@Override
	public String toString()
	{
		return "login=" + this.login + ", password=" + this.password
				+ ", firstName=" + this.firstName + ", lastName=" + this.lastName;
	}
}