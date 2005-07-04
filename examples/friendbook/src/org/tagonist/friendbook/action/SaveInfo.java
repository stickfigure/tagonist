/*
 * $Id: SignupSubmit.java,v 1.3 2002/06/06 12:23:53 lhoriman Exp $
 * $Source: /cvsroot/mav/maverick/examples/friendbook-jsp/javasrc/org/infohazard/friendbook/ctl/SignupSubmit.java,v $
 */

package org.tagonist.friendbook.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.tagonist.friendbook.data.Friend;

/**
 * This action saves all the information submitted.
 */
public class SaveInfo extends AuthRequired
{
	/** */
	public static class Model extends ErrorMapModel
	{
		/** */
		String firstName;
		public String getFirstName() { return this.firstName; }
		public void setFirstName(String value) { this.firstName = value; }
	
		/** */
		String lastName;
		public String getLastName() { return this.lastName; }
		public void setLastName(String value) { this.lastName = value; }
	
		/** */
		String addrLine1;
		public String getAddrLine1() { return this.addrLine1; }
		public void setAddrLine1(String value) { this.addrLine1 = value; }
	
		/** */
		String addrLine2;
		public String getAddrLine2() { return this.addrLine2; }
		public void setAddrLine2(String value) { this.addrLine2 = value; }
	
		/** */
		String addrCity;
		public String getAddrCity() { return this.addrCity; }
		public void setAddrCity(String value) { this.addrCity = value; }
	
		/** */
		String addrState;
		public String getAddrState() { return this.addrState; }
		public void setAddrState(String value) { this.addrState = value; }
	
		/** */
		String[] phoneList;
		public String[] getPhoneList() { return this.phoneList; }
		public void setPhoneList(String[] value) { this.phoneList = value; }
		
		/** */
		String[] emailList;
		public String[] getEmailList() { return this.emailList; }
		public void setEmailList(String[] value) { this.emailList = value; }
		
		/** */
		int[] delPhone;
		public int[] getDelPhone() { return this.delPhone; }
		public void setDelPhone(int[] value) { this.delPhone = value; }
		
		/** */
		int[] delEmail;
		public int[] getDelEmail() { return this.delEmail; }
		public void setDelEmail(int[] value) { this.delEmail = value; }
	}
	
	/** Ensure that the correct model class gets populated */
	public void initialize()
	{
		this.getCtx().setModel(new Model());
	}
	
	/**
	 */
	public void authExecute() throws Exception
	{
		Model model = (Model)this.getCtx().getModel();
		
		// You could try to validate some of this information here.
		
		Friend me = this.getMe();
		
		me.setFirstName(model.getFirstName());
		me.setLastName(model.getLastName());

		me.getAddress().setAddressLine1(model.getAddrLine1());
		me.getAddress().setAddressLine2(model.getAddrLine2());
		me.getAddress().setCity(model.getAddrCity());
		me.getAddress().setState(model.getAddrState());

		// Process the input phone list
		List phoneList = model.getPhoneList() == null
			? Collections.EMPTY_LIST
			: new ArrayList(Arrays.asList(model.getPhoneList()));	// must be mutable
		
		if (model.getDelPhone() != null)
			this.deleteIndexes(phoneList, model.getDelPhone());
		
		this.deleteBlanks(phoneList);
		
		me.setPhoneList(phoneList);
		
		// Process the input email list
		List emailList = model.getEmailList() == null
			? Collections.EMPTY_LIST
			: new ArrayList(Arrays.asList(model.getEmailList()));	// must be mutable
		
		if (model.getDelEmail() != null)
			this.deleteIndexes(emailList, model.getDelEmail());
		
		this.deleteBlanks(emailList);
		
		me.setEmailList(emailList);
	}

	/**
	 * Removes from the List the items at the specified indexes
	 */
	protected void deleteIndexes(List stuff, int[] indexesToDelete)
	{
		Arrays.sort(indexesToDelete);
		
		// Now go backwards becase we want to process highest index last
		for (int i=indexesToDelete.length-1; i>= 0; i--)
			stuff.remove(indexesToDelete[i]);
	}
	
	/**
	 * Removes from the list any null or empty strings
	 */
	protected void deleteBlanks(List stuff)
	{
		Iterator it = stuff.iterator();
		while (it.hasNext())
		{
			String foo = (String)it.next();
			if (foo == null || foo.trim().length() == 0)
				it.remove();
		}
	}
}