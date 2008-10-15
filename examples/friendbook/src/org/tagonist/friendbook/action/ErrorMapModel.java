/*
 */

package org.tagonist.friendbook.action;

import java.util.Collections;
import java.util.Map;

/**
 * Models with a simple error map.
 *
 * @author Jeff Schnitzer
 */
public class ErrorMapModel
{
	/** */
	Map<String, String> errors;

	/** */
	public Map<String, String> getErrors()
	{
		if (this.errors == null)
			return Collections.emptyMap();
		else
			return this.errors;
	}

	/** */
	public void setError(String key, String msg)
	{
		if (this.errors == null)
			this.errors = Collections.emptyMap();

		this.errors.put(key, msg);
	}
}
