/*
 * $Id: ControllerAuth.java,v 1.4 2004/06/07 20:45:36 eelco12 Exp $
 * $Source: /cvsroot/mav/maverick/examples/friendbook-jsp/javasrc/org/infohazard/friendbook/ctl/ControllerAuth.java,v $
 */

package org.tagonist.friendbook.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.tagonist.AbstractAction;
import org.tagonist.friendbook.data.Friend;
import org.tagonist.friendbook.data.FriendBook;

/**
 * Action support class which provides basic authentication services
 * to subclasses.
 */
abstract public class FriendbookAction extends AbstractAction
{
	/**
	 * The name of the session attribute which stores the login name.
	 */
	protected static final String LOGIN_ATTRNAME = "loginName";

	/**
	 * @return the login of the user currently logged in, or null if no
	 *	user is logged in.
	 */
	protected String currentLoginName()
	{
		return (String)this.getCtx().getRequest().getSession().getAttribute(LOGIN_ATTRNAME);
	}

	/**
	 * Try to log in with the specified credentials.
	 * @return true if it worked, false if the credentials are bad.
	 */
	protected boolean login(String login, String password)
	{
		FriendBook book = FriendBook.getBook();

		Friend f = book.findByLogin(login);

		if (f == null || !f.getPassword().equals(password))
		{
			return false;
		}
		else
		{
			this.getCtx().getRequest().getSession().setAttribute(LOGIN_ATTRNAME, login);
			return true;
		}
	}

	/**
	 */
	protected void logout()
	{
		this.getCtx().getRequest().getSession().removeAttribute(LOGIN_ATTRNAME);
	}
	
	/**
	 */
	protected boolean isLoggedIn()
	{
		return this.getCtx().getRequest().getSession().getAttribute(LOGIN_ATTRNAME) != null;
	}

	/**
	 * @return the full URI, including the query string.  Works even
	 *  if an http POST was submitted.
	 */
	protected String getFullRequestURI()
	{
		if ("POST".equals(this.getCtx().getRequest().getMethod().toUpperCase()))
		{
			Map<String, String[]> params = this.getCtx().getRequest().getParameterMap();
			
			if (params.isEmpty())
			{
				return this.getRelativeRequestURI();
			}
			else
			{
				// We have to build the query string from the parameters by hand
				StringBuffer queryBuf = new StringBuffer(1024);
				queryBuf.append(this.getRelativeRequestURI());
	
				boolean afterFirst = false;
				for (Map.Entry<String, String[]> entry: params.entrySet())
				{
					for (String value: entry.getValue())
					{
						if (afterFirst)
						{
							queryBuf.append('&');
						}
						else
						{
							queryBuf.append('?');
							afterFirst = true;
						}
						
						try
						{
							queryBuf.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
							queryBuf.append('=');
							queryBuf.append(URLEncoder.encode(value, "UTF-8"));
						}
						catch (UnsupportedEncodingException ex)
						{
							// Should be impossible
							throw new RuntimeException(ex);
						}
					}
				}
				
				return queryBuf.toString();
			}
		}
		else
		{
			String query = this.getCtx().getRequest().getQueryString();
			if (query == null)
				return this.getRelativeRequestURI();
			else
				return this.getRelativeRequestURI() + "?" + query;
		}
	}
	
	/**
	 * @return the value of HttpServletRequest.getRequestURI() minus the context path.
	 */
	protected String getRelativeRequestURI()
	{
		int ctxPathLen = this.getCtx().getRequest().getContextPath().length();
		// Add one because we want to strip off the extra '/'
		return this.getCtx().getRequest().getRequestURI().substring(ctxPathLen + 1);
	}
}