/*
 */
package org.tagonist;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Client developers typically extend this class rather than implement Action
 * themselves.  It provides for population of bean properties on the model
 * from the http request parameters.
 * 
 * @author Jeff Schnitzer
 */
public abstract class AbstractAction implements Action
{
	/**
	 * Maintain this as a member variable so it need not be passed around.
	 */
	private ActionContext ctx;
	
	/**
	 * Provide access to the context to any subclasses.
	 */
	protected ActionContext getCtx() { return this.ctx; }
	
	/** 
	 */
	abstract public void execute() throws Exception;
	
	/**
	 * Called prior to bean population of the model.  If you would like a different
	 * object populated, call getCtx().setModel() from here.
	 */
	protected void initialize() throws Exception
	{
		// Do nothing by default
	}
	
	/**
	 * Override execute() instead.
	 * 
	 * @see org.tagonist.Action#execute(org.tagonist.ActionContext)
	 */
	public final void execute(ActionContext ctx) throws Exception
	{
		this.ctx = ctx;
		
		this.getCtx().setModel(this);
		
		this.initialize();
		
		Object model = this.getCtx().getModel();
		if (model != null)
		{
			BeanUtils.populate(model, this.getCtx().getRequest().getParameterMap());
			BeanUtils.populate(model, this.getCtx().getActionParams());
		}
	
		this.execute();
	}
}
