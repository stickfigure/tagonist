/*
 */
package org.tagonist;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.BigIntegerConverter;
import org.apache.commons.beanutils.converters.BooleanArrayConverter;
import org.apache.commons.beanutils.converters.BooleanConverter;
import org.apache.commons.beanutils.converters.ByteArrayConverter;
import org.apache.commons.beanutils.converters.ByteConverter;
import org.apache.commons.beanutils.converters.CharacterArrayConverter;
import org.apache.commons.beanutils.converters.CharacterConverter;
import org.apache.commons.beanutils.converters.ClassConverter;
import org.apache.commons.beanutils.converters.DoubleArrayConverter;
import org.apache.commons.beanutils.converters.DoubleConverter;
import org.apache.commons.beanutils.converters.FileConverter;
import org.apache.commons.beanutils.converters.FloatArrayConverter;
import org.apache.commons.beanutils.converters.FloatConverter;
import org.apache.commons.beanutils.converters.IntegerArrayConverter;
import org.apache.commons.beanutils.converters.IntegerConverter;
import org.apache.commons.beanutils.converters.LongArrayConverter;
import org.apache.commons.beanutils.converters.LongConverter;
import org.apache.commons.beanutils.converters.ShortArrayConverter;
import org.apache.commons.beanutils.converters.ShortConverter;
import org.apache.commons.beanutils.converters.SqlDateConverter;
import org.apache.commons.beanutils.converters.SqlTimeConverter;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.beanutils.converters.StringArrayConverter;
import org.apache.commons.beanutils.converters.StringConverter;
import org.apache.commons.beanutils.converters.URLConverter;
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
