/*
 */
package org.tagonist;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * JSP custom tag designed to be nested within an ActionTag, allows passing
 * special parameters which can be recognized by actions.  Not the same as
 * http request parameters, although Action implementations may choose to
 * populate models from this collection.
 * 
 * @author Jeff Schnitzer
 */
@SuppressWarnings("serial")
public class ParamTag extends TagSupport
{
	/** */
	protected String name;
	public String getName() { return this.name; }
	public void setName(String value) { this.name = value; }
	
	/** */
	protected Object value;
	public Object getValue() { return this.value; }
	public void setValue(Object value) { this.value = value; }

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException
	{
		((ActionTag)this.getParent()).setActionParam(this.name, this.value);
		
		return EVAL_PAGE;
	}
}
