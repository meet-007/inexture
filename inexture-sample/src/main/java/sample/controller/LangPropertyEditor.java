package sample.controller;

import java.beans.PropertyEditorSupport;

import sample.models.LangMaster;

// TODO: Auto-generated Javadoc
/**
 * The Class LangPropertyEditor.
 */
public class LangPropertyEditor extends PropertyEditorSupport{

	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 */
	@Override
	public String getAsText() {

		final LangMaster lmaster = (LangMaster)getValue();
		return Long.toString(lmaster.getIdlang());
	}

	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	@Override
	public void setAsText(final String text) {
		final LangMaster lmaster = new LangMaster();
		lmaster.setIdlang(Long.parseLong(text));
		setValue(lmaster);
	}
}
