/*
 */
package org.tagonist;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

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
	public Map getActionParams();
	
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
