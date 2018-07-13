package sample.controller;

import java.beans.PropertyEditorSupport;

import sample.models.User;

public class UserPropertyEditor extends PropertyEditorSupport{
	@Override
	public String getAsText() {

		final User user = (User)getValue();
		return Long.toString(user.getIduser());
	}

	@Override
	public void setAsText(String text) {
		final User user = new User();
		user.setIduser(Long.parseLong(text));
		setValue(user);
	}
}
