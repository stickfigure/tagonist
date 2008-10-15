/*
 * $Id: SignupSubmit.java,v 1.3 2002/06/06 12:23:53 lhoriman Exp $
 * $Source: /cvsroot/mav/maverick/examples/friendbook-jsp/javasrc/org/infohazard/friendbook/ctl/SignupSubmit.java,v $
 */

package org.tagonist.friendbook.action;

import org.tagonist.friendbook.data.Friend;

/**
 * This action gets all the data from the friendbook and creates
 * a model identical to the one which will be used for SaveInfo.
 */
public class GetInfo extends AuthRequired
{
	/**
	 * Populate a model and hand it back.
	 */
	@Override
	public void authExecute() throws Exception
	{
		SaveInfo.Model model = new SaveInfo.Model();

		Friend me = this.getMe();

		model.setFirstName(me.getFirstName());
		model.setLastName(me.getLastName());
		model.setAddrLine1(me.getAddress().getAddressLine1());
		model.setAddrLine2(me.getAddress().getAddressLine2());
		model.setAddrCity(me.getAddress().getCity());
		model.setAddrState(me.getAddress().getState());

		model.setPhoneList(me.getPhoneList().toArray(new String[0]));
		model.setEmailList(me.getEmailList().toArray(new String[0]));

		this.getCtx().setModel(model);
	}
}