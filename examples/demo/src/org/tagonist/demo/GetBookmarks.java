/*
 */

package com.voodoodyne.tagonist.demo;

import java.util.ArrayList;
import java.util.List;

import com.voodoodyne.tagonist.AbstractAction;

/**
 * Gets a hypothetical user object and makes it available as the model.
 *
 * @author Jeff Schnitzer
 */
public class GetBookmarks extends AbstractAction
{
	/** */
	long id;
	public void setId(long value) { this.id = value; }

	/** */
	@Override
	public void execute() throws Exception
	{
		List<Bookmark> marks = this.fetchBookmarks(this.id);

		this.getCtx().setModel(marks);
	}

	/**
	 * Could fetch the data from a database, etc
	 */
	List<Bookmark> fetchBookmarks(long userId)
	{
		if (userId == 1)
		{
			List<Bookmark> marks = new ArrayList<Bookmark>();
			marks.add(new Bookmark("Memorize This", "http://www.wikipedia.org/"));
			marks.add(new Bookmark("Dance", "http://www.lindylist.com/"));
			return marks;
		}
		else if (userId == 2)
		{
			List<Bookmark> marks = new ArrayList<Bookmark>();
			marks.add(new Bookmark("Furniture Porn", "http://www.furnitureporn.com/"));
			marks.add(new Bookmark("Who are you looking at?", "http://127.0.0.1/"));
			return marks;
		}
		else
		{
			List<Bookmark> marks = new ArrayList<Bookmark>();
			marks.add(new Bookmark("Tagonist", "http://tagonist.tigris.org/"));
			marks.add(new Bookmark("Pet Project", "http://www.similarity.net/"));
			marks.add(new Bookmark("Internet Radio", "http://www.badgerbadgerbadger.com/"));
			return marks;
		}
	}
}
