/*
 */

package org.tagonist.friendbook.action;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Models with a simple error map.
 * 
 * @author Jeff Schnitzer
 */
public class ErrorMapModel 
{
	/** */
	Map errors;
	
	/** */
	public Map getErrors()
	{
		if (this.errors == null)
			return Collections.EMPTY_MAP;
		else
			return this.errors;
	}

	/** */
	public void setError(String key, String msg)
	{
		if (this.errors == null)
			this.errors = new HashMap();
		
		this.errors.put(key, msg);
	}
}
