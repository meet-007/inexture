package sample.services;

import java.util.List;

import sample.models.TechMaster;

// TODO: Auto-generated Javadoc
/**
 * The Interface TechServ.
 */
public interface TechService {

	/**
	 * Gets the tech.
	 *
	 * @return the tech
	 */
	public List<TechMaster> findAll();
}
