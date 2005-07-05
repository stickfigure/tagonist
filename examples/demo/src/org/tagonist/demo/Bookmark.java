/*
 */

package org.tagonist.demo;


/**
 * Hypothetical data object for a bookmark.
 * 
 * @author Jeff Schnitzer
 */
public class Bookmark
{
	String title;
	String url;
	
	public Bookmark(String title, String url)
	{
		this.title = title;
		this.url = url;
	}
	
	public String getTitle() { return this.title; }
	public String getUrl() { return this.url; }
}
