/*
 */
package org.tagonist;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * JSP custom tag that instantiates Action classes.
 * 
 * @author Jeff Schnitzer
 */
public class ActionTag extends TagSupport implements ActionContext
{
	/** */
	private static Log log = LogFactory.getLog(ActionTag.class);
	
	/** */
	public final String SCOPE_PAGE = "page";
	public final String SCOPE_REQUEST = "request";
	public final String SCOPE_SESSION = "session";
	public final String SCOPE_APPLICATION = "application";
	
	/** */
	protected String var;
	public String getVar() { return this.var; }
	public void setVar(String value) { this.var = value; }
	
	/** */
	protected String type;
	public String getType() { return this.type; }
	public void setType(String value) { this.type = value; }

	/** */
	protected String scope;
	public String getScope() { return this.scope; }
	public void setScope(String value) { this.scope = value; }
	
	/** */
	protected boolean recycle;
	public boolean getRecycle() { return this.recycle; }
	public void setRecycle(boolean value) { this.recycle = value; }

	/**
	 * Lazily created Map of action parameters.
	 */
	protected Map actionParams;
	
	/**
	 * The model that will (hopefully) be assigned by an action. 
	 */
	protected Object model;
	
	/**
	 * This needs to be set fairly quickly, then we ignore the textual
	 * scope property.
	 */
	protected int pageContextScope = PageContext.REQUEST_SCOPE;
	
	/* (non-Javadoc)
	 * @see org.tagonist.ActionContext#setModel(java.lang.Object)
	 */
	public void setModel(Object value)
	{
		if (log.isDebugEnabled())
			log.debug("Setting model to " + value);

		this.model = value;
		
		// Keep the page context scope up to date
		if (this.var != null)
			this.pageContext.setAttribute(this.var, this.model, this.pageContextScope);
	}

	/* (non-Javadoc)
	 * @see org.tagonist.ActionContext#getModel()
	 */
	public Object getModel() { return this.model; }

	/* (non-Javadoc)
	 * @see org.tagonist.ActionContext#getActionParams()
	 */
	public Map getActionParams()
	{
		if (this.actionParams == null)
			return Collections.EMPTY_MAP;
		else
			return this.actionParams;
	}
	
	/**
	 * Used only by nested ParamTag to pass parameters up.
	 */
	void setActionParam(String name, Object value)
	{
		if (this.actionParams == null)
			this.actionParams = new HashMap();
		
		if (log.isDebugEnabled())
			log.debug("With param " + name + ":  " + value);
		
		this.actionParams.put(name, value);
	}

	/* (non-Javadoc)
	 * @see org.tagonist.ActionContext#getPageContext()
	 */
	public PageContext getPageContext()
	{
		return this.pageContext;
	}

	/* (non-Javadoc)
	 * @see org.tagonist.ActionContext#getRequest()
	 */
	public HttpServletRequest getRequest()
	{
		return (HttpServletRequest)this.pageContext.getRequest();
	}

	/* (non-Javadoc)
	 * @see org.tagonist.ActionContext#getResponse()
	 */
	public HttpServletResponse getResponse()
	{
		return (HttpServletResponse)this.pageContext.getResponse();
	}

	/* (non-Javadoc)
	 * @see org.tagonist.ActionContext#getSession()
	 */
	public HttpSession getSession()
	{
		return this.getRequest().getSession();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doStartTag()
	 */
	public int doStartTag() throws JspException
	{
		if (this.actionParams != null)
			this.actionParams.clear();
	
		return EVAL_BODY_INCLUDE;
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException
	{
		// Figure out this.pageContextScope immediately
		if (this.scope != null)
		{
			if (SCOPE_PAGE.equals(this.scope))
				this.pageContextScope = PageContext.PAGE_SCOPE;
			else if (SCOPE_REQUEST.equals(this.scope))
				this.pageContextScope = PageContext.REQUEST_SCOPE;
			else if (SCOPE_SESSION.equals(this.scope))
				this.pageContextScope = PageContext.SESSION_SCOPE;
			else if (SCOPE_APPLICATION.equals(this.scope))
				this.pageContextScope = PageContext.APPLICATION_SCOPE;
		}
		
		// Check to see if we should just recycle existing object
		if (this.recycle)
		{
			if (this.var == null)
				throw new IllegalStateException("You cannot recycle if you do not specify a var");
			
			if (this.pageContext.findAttribute(this.var) != null)
				return SKIP_BODY;
		}
		
		try
		{
			Class actionClass = Class.forName(this.type);
			Action act = (Action)actionClass.newInstance();

			if (log.isDebugEnabled())
				log.debug("Executing action class " + actionClass.getName());
				
			// Note that when the action sets the model, it will
			// at that time establish the context attribute.
			act.execute(this);

			return EVAL_PAGE;
		}
		catch (ClassNotFoundException ex)
		{
			log.error("Problem with action " + this.type, ex);
			throw new JspException(ex);
		}
		catch (InstantiationException ex)
		{
			log.error("Problem with action " + this.type, ex);
			throw new JspException(ex);
		}
		catch (IllegalAccessException ex)
		{
			log.error("Problem with action " + this.type, ex);
			throw new JspException(ex);
		}
		catch (ForwardException ex)
		{
			if (log.isDebugEnabled())
				log.debug("Forwarding to " + ex.getPath());
			
			try
			{
				this.pageContext.forward(ex.getPath());
			}
			catch (ServletException sex) { throw new JspException(sex); }
			catch (IOException iox) { throw new JspException(iox); }
			
			return SKIP_PAGE;
		}
		catch (RedirectException ex)
		{
			if (log.isDebugEnabled())
				log.debug("Redirecting to " + ex.getPath());
			
			try
			{
				this.getResponse().sendRedirect(ex.getPath());
			}
			catch (IOException iox) { throw new JspException(iox); }
			
			return SKIP_PAGE;
		}
		catch (SkipPageException ex)
		{
			return SKIP_PAGE;
		}
		catch (RuntimeException ex)
		{
			log.error("Problem with action " + this.type, ex);
			// No need to catch these
			throw ex;
		}
		catch (Exception ex)
		{
			log.error("Problem with action " + this.type, ex);
			throw new JspException(ex);
		}
	}
}
