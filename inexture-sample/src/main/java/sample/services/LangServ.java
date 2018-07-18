package sample.services;

import java.util.List;

import sample.models.LangMaster;



// TODO: Auto-generated Javadoc
/**
 * The Interface LangServ.
 */
public interface LangServ {

	/**
	 * Gets the lang.
	 *
	 * @return the lang
	 */
	List<LangMaster> findAll();
}
