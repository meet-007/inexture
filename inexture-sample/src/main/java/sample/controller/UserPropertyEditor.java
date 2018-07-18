package sample.controller;

import java.beans.PropertyEditorSupport;

import sample.models.User;

// TODO: Auto-generated Javadoc
/**
 * The Class UserPropertyEditor.
 */
public class UserPropertyEditor extends PropertyEditorSupport{

	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {

		final User user = (User)getValue();
		return Long.toString(user.getIduser());
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(final String text) {
		final User user = new User();
		user.setIduser(Long.parseLong(text));
		setValue(user);
	}
}
