/*
 */
package com.voodoodyne.tagonist;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.util.Map;

/**
 * Interface that provides container APIs to action classes.
 *
 * @author Jeff Schnitzer
 */
public interface ActionContext
{
	/**
	 * Assigns an object to be the model for rendering in the view.
	 */
	public void setModel(Object value);

	/**
	 * @return the model set by this action.
	 */
	public Object getModel();

	/**
	 * @return a Map of parameters passed into the action using the
	 * 	param tag.  Not to be confused with http parameters.
	 */
	public Map<String, Object> getActionParams();

	/**
	 * Sets a key/value error flag.
	 */
	public void setError(String errorName, Object value);

	/**
	 * Gets an error or null if no error with that name has been set.
	 */
	public Object getError(String errorName);

	/**
	 * Gets a list of all errors
	 */
	public Map<String, Object> getErrors();

	/**
	 * Removes an error
	 */
	public void removeError(String errorName);

	/**
	 * Do we have any errors?
	 */
	public boolean hasErrors();

	/**
	 * @return the JSP page context.
	 */
	public PageContext getPageContext();

	/**
	 * Convenience method.
	 */
	public HttpServletRequest getRequest();

	/**
	 * Convenience method.
	 */
	public HttpServletResponse getResponse();

	/**
	 * Convenience method.
	 */
	public HttpSession getSession();
}
