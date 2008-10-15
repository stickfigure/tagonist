/*
 */
package org.tagonist;

import org.tagonist.beanutils.ErrorTrappingBeanUtilsBean;

/**
 * This action replaces AbstractAction.  Instead of ignoring or throwing
 * an exception when http parameters cannot be converted to the correct
 * model type, an error message is put in the error collection.
 *
 * @author Jeff Schnitzer
 */
public abstract class ErrorTrappingAction implements Action
{
	/** Smarter than the average BeanUtilsBean */
	protected static ErrorTrappingBeanUtilsBean beanUtils = new ErrorTrappingBeanUtilsBean();

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
	 * Called prior to bean population of the model. If you would like a
	 * different object populated, call getCtx().setModel() from here.
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
	@SuppressWarnings("unchecked")
	public final void execute(ActionContext ctx) throws Exception
	{
		this.ctx = ctx;

		this.getCtx().setModel(this);

		this.initialize();

		Object model = this.getCtx().getModel();
		if (model != null)
		{
			beanUtils.populate(model, this.getCtx().getRequest().getParameterMap(), this.getCtx());
			beanUtils.populate(model, this.getCtx().getActionParams(), this.getCtx());
		}

		this.execute();
	}
}
