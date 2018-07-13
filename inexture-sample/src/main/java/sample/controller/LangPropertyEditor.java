package sample.controller;

import java.beans.PropertyEditorSupport;

import sample.models.LangMaster;

public class LangPropertyEditor extends PropertyEditorSupport{
	@Override
	public String getAsText() {

		final LangMaster lmaster = (LangMaster)getValue();
		return Long.toString(lmaster.getIdlang());
	}

	@Override
	public void setAsText(String text) {
		final LangMaster lmaster = new LangMaster();
		lmaster.setIdlang(Long.parseLong(text));
		setValue(lmaster);
	}
}
