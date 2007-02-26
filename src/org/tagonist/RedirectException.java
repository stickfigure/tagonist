/*
 */
package org.tagonist;

/**
 * Throwing a RedirectException from within the body of Action.execute() will
 * result in a client-side redirect.  Be cautious that the response buffer has
 * not already been committed.
 * 
 * @author Jeff Schnitzer
 */
@SuppressWarnings("serial")
public class RedirectException extends RuntimeException
{
	/**
	 * @param path must be suitable for passing to HttpServletResponse.sendRedirect(). 
	 */
	public RedirectException(String path)
	{
		super(path);
	}
	
	/**
	 */
	public String getPath()
	{
		return this.getMessage();
	}
}
