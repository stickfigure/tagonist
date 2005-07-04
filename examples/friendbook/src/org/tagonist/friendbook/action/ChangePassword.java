/*
 * $Id: SignupSubmit.java,v 1.3 2002/06/06 12:23:53 lhoriman Exp $
 * $Source: /cvsroot/mav/maverick/examples/friendbook-jsp/javasrc/org/infohazard/friendbook/ctl/SignupSubmit.java,v $
 */

package org.tagonist.friendbook.action;

import org.tagonist.friendbook.data.Friend;

/**
 * Change a password.
 */
public class ChangePassword extends AuthRequired
{
	/** */
	public static class Model extends ErrorMapModel
	{
		/** */
		String oldPassword = "";
		public String getOldPassword() { return this.oldPassword; }
		public void setOldPassword(String value) { this.oldPassword = value; }
	
		/** */
		String newPassword = "";
		public String getNewPassword() { return this.newPassword; }
		public void setNewPassword(String value) { this.newPassword = value; }
	
		/** */
		String newPasswordAgain = "";
		public String getNewPasswordAgain() { return this.newPasswordAgain; }
		public void setNewPasswordAgain(String value) { this.newPasswordAgain = value; }
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
		
		if (model.getNewPassword().length() < 4)
			model.setError("newPassword", "Your password is too short");
		
		if (!model.getNewPassword().equals(model.getNewPasswordAgain()))
		{
			model.setError("newPasswordAgain", "Your passwords did not match");
			model.setNewPassword("");
			model.setNewPasswordAgain("");
		}
		
		if (model.getErrors().isEmpty())
		{
			Friend me = this.getMe();
			
			if (!me.getPassword().equals(model.getOldPassword()))
			{
				model.setError("oldPassword", "Old password incorrect");
				model.setOldPassword("");
			}
			else
			{
				me.setPassword(model.getNewPassword());
			}
		}
	}
}