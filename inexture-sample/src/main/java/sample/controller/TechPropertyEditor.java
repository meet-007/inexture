package sample.controller;

import java.beans.PropertyEditorSupport;

import sample.models.TechMaster;

public class TechPropertyEditor extends PropertyEditorSupport{
	@Override
	public String getAsText() {

		final TechMaster tmaster = (TechMaster)getValue();
		return Long.toString(tmaster.getIdtech());
	}

	@Override
	public void setAsText(String text) {
		final TechMaster tmaster = new TechMaster();
		tmaster.setIdtech(Long.parseLong(text));
		setValue(tmaster);
	}
}
