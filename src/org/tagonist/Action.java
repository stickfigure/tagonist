/*
 */

package org.tagonist;

/**
 * Actions must implement this interface in order to be processed
 * by the Tagonist framework.  While you may wish to implement this
 * interface directly, you probably should extend AbstractAction instead.
 */
public interface Action
{
	/**
	 * @throws RedirectException, ForwardException 
	 */
	public void execute(ActionContext ctx) throws Exception;
}