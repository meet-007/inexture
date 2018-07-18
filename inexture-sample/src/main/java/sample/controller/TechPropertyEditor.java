package sample.controller;

import java.beans.PropertyEditorSupport;

import sample.models.TechMaster;

// TODO: Auto-generated Javadoc
/**
 * The Class TechPropertyEditor.
 */
public class TechPropertyEditor extends PropertyEditorSupport{

	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {

		final TechMaster tmaster = (TechMaster)getValue();
		return Long.toString(tmaster.getIdtech());
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(final String text) {
		final TechMaster tmaster = new TechMaster();
		tmaster.setIdtech(Long.parseLong(text));
		setValue(tmaster);
	}
}
