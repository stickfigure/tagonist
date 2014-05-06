/*
 */
package com.voodoodyne.tagonist;

/**
 * Throwing a SkipPageException from within the body of Action.execute() will
 * prevent execution of the remainder of the JSP.  We use this rather than
 * javax.servlet.jsp.SkipPageException because this extends RuntimeException.
 * 
 * @author Jeff Schnitzer
 */
@SuppressWarnings("serial")
public class SkipPageException extends RuntimeException
{
}
