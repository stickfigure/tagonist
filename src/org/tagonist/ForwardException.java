/*
 */
package org.tagonist;

/**
 * Throwing a ForwardException from within the body of Action.execute() will
 * result in a server-side forward.  Be cautious that the response buffer has
 * not already been committed.
 * 
 * @author Jeff Schnitzer
 */
public class ForwardException extends RuntimeException
{
	/**
	 * @param path must be suitable for passing to PageContext.forward() 
	 */
	public ForwardException(String path)
	{
		super(path);
	}
	
	/**
	 * @return the path to forward to
	 */
	public String getPath() { return this.getMessage(); }
}
