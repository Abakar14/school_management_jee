package de.bytmasoft.school.dao.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;
import javax.persistence.EntityManager;

@Local
public interface BytmasoftServiceLocal<T, ID extends Serializable> {

	/**
	 * Gets the entity class.
	 *
	 * @return the entity class
	 */
	public Class<T> getEntityClass();
	
	/**
	 * Count all.
	 *
	 * @return a Number of existing Objects
	 */
	public int countAll();	
	
	
	/**
	 * Creates the.
	 *
	 * @param entity object to create
	 * @return new entity of the T type
	 */
	public T create(T entity); 
		
	/**
	 * Delete.
	 *
	 * @param entity an Object for deleting
	 */
	public void delete(T entity);
	
	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	public void delete(Long id);
	
	/**
	 * Delete all.
	 *
	 * @param type for deleting
	 * @return please take care for using this Method
	 * it will going to delete all Objects
	 */
	public int deleteAll(Class<T> type); 
	
	
	/**
	 * Find all.
	 *
	 * @param type of entity class
	 * @return List of Objects which will be found
	 */
	public List<T> findAll(Class<T> type);
	
	
	
	/**
	 * Find by column name.
	 *
	 * @param queryName as string
	 * @param parameters  as list
	 * @return list of Objects form Type T
	 */
	public List<T> findByColumnName(String queryName, Map<String, ? extends Object> parameters); 
	

	/**
	 * Find by id.
	 *
	 * @param id of the Object or the primary key of the Table which this Object belong to
	 * @return the T Object Type
	 */
	public T findById(Long id);
	
	/**
	 * Find by named query.
	 *
	 * @param queryName the name of namedQuery
	 * @param firstResult use as index for search Objects eg (form = 1 to ?)
	 * @param maxResult use as upper index for the searching eg (to = 50)
	 * @return List
	 */
	public	List<T> findByNamedQuery(String queryName, int firstResult, int maxResult);
	
	
	
	/**
	 * Find by named query.
	 *
	 * @param queryName the name of namedQuery
	 * @return list of T objects
	 */
	public  List<T> findByNamedQuery(String queryName);
	
	
	/**
	 * Find by named query.
	 *
	 * @param queryName the name of namedQuery
	 * @param resultLimit number of objects do you want
	 * @return list of object of type T
	 */
	public List<T> findByNamedQuery(String queryName , int resultLimit);
	
	
	/**
	 * Find by named query.
	 *
	 * @param queryName the name of namedQuery
	 * @param parameters your parameters to set
	 * @return list of objects of type T
	 */

	public  List<T> findByNamedQuery(String queryName, Map<String, ? extends Object> parameters);
	
	
	/**
	 * Find by named query.
	 *
	 * @param queryName the name of namedQuery
	 * @param parameters your parameters to set
	 * @param resultLimit number of objects do you want
	 * @return list of objects of Type T
	 */
	public List<T> findByNamedQuery(String queryName, Map<String, ? extends Object> parameters, int resultLimit);
	
			
	/**
	 * Find by native query.
	 *
	 * @param guery normal SQL statement
	 * @return list of objects of type T
	 */
	public List<T> findByNativeQuery(String guery);
	
	/**
	 * Sets the entity manager.
	 *
	 * @param em EntityManager type
	 */	
	public void setEntityManager(EntityManager em);
	
	/**
	 * Persist.
	 *
	 * @param entities are list of Objects to persisting
	 */
	public void persist(List<T> entities);
	
	/**
	 * Persist.
	 *
	 * @param entity is an Object to persisting
	 */
	public void persist(T entity);
	
	/**
	 * Update.
	 *
	 * @param entity an Object to updating
	 * @return list of Objects
	 */
	public T update(T entity);
	
}
