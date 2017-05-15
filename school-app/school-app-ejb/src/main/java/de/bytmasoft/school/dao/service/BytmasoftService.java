package de.bytmasoft.school.dao.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.lang.reflect.ParameterizedType;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class BytmasoftService
 */
@Stateless
@LocalBean
public class BytmasoftService <T, ID extends Serializable> implements BytmasoftServiceRemote, BytmasoftServiceLocal<T, Serializable> {

	/** The Constant log. */
//	private static final Logger log = Logger.getLogger(BytmasoftDAOBean.class	.getName());

		
	/** The em. */
	@PersistenceContext
	private EntityManager em;
	
	/** The persistent class. */
	private Class<T> persistentClass;	
    /**
     * Default constructor. 
     */
    @SuppressWarnings("unchecked")
	public BytmasoftService() {
    	this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#getEntityClass()
	 */
	@Override
	public Class<T> getEntityClass() {
		return persistentClass;
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#countAll()
	 */
	@Override
	public int countAll() {
		return ((Long) em.createQuery(
				"select  count (t) from " + getEntityClass().getSimpleName()
						+ " t").getSingleResult()).intValue();
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#create(java.lang.Object)
	 */
	@Override
	public T create(T entity) {
		this.em.persist(entity);

		this.em.flush();
		this.em.refresh(entity);
		return entity;
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#delete(java.lang.Object)
	 */
	@Override
	public void delete(T entity) {
		//log.info("Delete Entity has been started .....");

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			entity = em.merge(entity);
			em.remove(entity);
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			//	log.info("there is some problem by deleting the Entity and Rollback will been been started .....");

			}

			//log.info("There is some problem occurred during execute the query...."
				//	+ ex);
			throw ex;

		} finally {
			em.close();
			//log.info("EntityManager is closed bei delete Entity.....");

		}
		
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#delete(java.lang.Long)
	 */
	@Override
	public void delete(Long id) {
		T toBeDeleted = findById(id);
		em.remove(toBeDeleted);
		
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#deleteAll(java.lang.Class)
	 */
	@Override
	public int deleteAll(Class type) {
//		log.info("Delete All Entities has been started .....");

		int value;
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			value = em.createQuery("DELETE FROM " + type.getSimpleName())
					.executeUpdate();
			tx.commit();
		} catch (RuntimeException ex) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
//				log.info("there is some problem by deleting all Entities and Rollback will been started .....");
			}
//			log.info("There is some problem occurred during execute the delete all query...."
//					+ ex);
			throw ex;
		} finally {
			em.close();
//			log.info("EntityManager is closed.....");
		}

		return value;
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#findAll(java.lang.Class)
	 */
	@Override
	public List findAll(Class type) {
//		log.info("findAll Entities has been started .....");
		return em.createQuery("SELECT t FROM " + type.getSimpleName() + " t").getResultList();

	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#findByColumnName(java.lang.String, java.util.Map)
	 */
	@Override
	public List findByColumnName(String queryName, Map<String, ? extends Object> parameters) {
		Query query = null;
//		log.info("NamedQuery : "+queryName);
		try {
		 query = this.em.createQuery(queryName);
			
	         for(Map.Entry<String, ? extends Object> entry : parameters.entrySet()){
	        	 query.setParameter(entry.getKey(), entry.getValue());
	         }
							
		} catch (Exception e) {
			e.printStackTrace();
//			log.error(e);
		}
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#findById(java.lang.Long)
	 */
	@Override
	public T findById(Long id) {
		return em.find(getEntityClass(), id);
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#findByNamedQuery(java.lang.String, int, int)
	 */
	@Override
	public List findByNamedQuery(String queryName, int firstResult, int maxResult) {
		Query query = this.em.createNamedQuery(queryName);
		if(firstResult > 0){
			query.setFirstResult(firstResult);
		}
		if(maxResult > 0){
			query.setMaxResults(maxResult);
		}
		
		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#findByNamedQuery(java.lang.String)
	 */
	@Override
	public List findByNamedQuery(String queryName) {
		return this.em.createNamedQuery(queryName).getResultList();
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#findByNamedQuery(java.lang.String, int)
	 */
	@Override
	public List findByNamedQuery(String queryName, int resultLimit) {
		return this.em.createNamedQuery(queryName).setMaxResults(resultLimit)
				.getResultList();
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#findByNamedQuery(java.lang.String, java.util.Map)
	 */
	@Override
	public List findByNamedQuery(String queryName, Map<String, ? extends Object> parameters) {
		return findByNamedQuery(queryName, parameters, 0);
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#findByNamedQuery(java.lang.String, java.util.Map, int)
	 */
	@Override
	public List findByNamedQuery(String queryName, Map<String, ? extends Object> parameters, int resultLimit) {
		Query query = this.em.createNamedQuery(queryName);

		if (resultLimit > 0) {
			query.setMaxResults(resultLimit);
		}

		for (Map.Entry<String, ? extends Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());

		}

		return query.getResultList();
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#findByNativeQuery(java.lang.String)
	 */
	@Override
	public List findByNativeQuery(String guery) {
		return this.em.createNativeQuery(guery, getEntityClass()).getResultList();
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#setEntityManager(javax.persistence.EntityManager)
	 */
	@Override
	public void setEntityManager(EntityManager em) {
		this.em = em;
		
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#persist(java.util.List)
	 */
	@Override
	public void persist(List <T>entities) {

		for(T entity : entities){
			persist(entity);
		}
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#persist(java.lang.Object)
	 */
	@Override
	public void persist(T entity) {
//		log.info("persist Entitiy has been started .....");

		if(em.contains(entity)){
			em.merge(entity);
		}else{
			em.persist(entity);
		}
		
	}

	/* (non-Javadoc)
	 * @see de.bytmasoft.school.dao.service.BytmasoftServiceLocal#update(java.lang.Object)
	 */
	@Override
	public T update(T entity) {
		return this.em.merge(entity);
	}

}
