/*
 */

package org.tagonist.demo;


/**
 * Hypothetical data object for a user.
 * 
 * @author Jeff Schnitzer
 */
public class User
{
	long id;
	String name;
	String email;
	
	public User(long id, String name, String email)
	{
		this.id = id;
		this.name = name;
		this.email = email;
	}
	
	public long getId() { return this.id; }
	public String getName() { return this.name; }
	public String getEmail() { return this.email; }
}
