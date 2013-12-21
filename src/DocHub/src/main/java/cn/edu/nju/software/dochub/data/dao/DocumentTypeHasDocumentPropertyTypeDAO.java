package cn.edu.nju.software.dochub.data.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.nju.software.dochub.data.dataobject.DocumentTypeHasDocumentPropertyType;

/**
 * A data access object (DAO) providing persistence and search support for
 * DocumentTypeHasDocumentPropertyType entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see cn.edu.nju.software.dochub.data.dataobject.DocumentTypeHasDocumentPropertyType
 * @author MyEclipse Persistence Tools
 */

public class DocumentTypeHasDocumentPropertyTypeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DocumentTypeHasDocumentPropertyTypeDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(DocumentTypeHasDocumentPropertyType transientInstance) {
		log.debug("saving DocumentTypeHasDocumentPropertyType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DocumentTypeHasDocumentPropertyType persistentInstance) {
		log.debug("deleting DocumentTypeHasDocumentPropertyType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocumentTypeHasDocumentPropertyType findById(java.lang.Integer id) {
		log.debug("getting DocumentTypeHasDocumentPropertyType instance with id: "
				+ id);
		try {
			DocumentTypeHasDocumentPropertyType instance = (DocumentTypeHasDocumentPropertyType) getHibernateTemplate()
					.get("cn.edu.nju.software.dochub.data.dataobject.DocumentTypeHasDocumentPropertyType",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DocumentTypeHasDocumentPropertyType> findByExample(
			DocumentTypeHasDocumentPropertyType instance) {
		log.debug("finding DocumentTypeHasDocumentPropertyType instance by example");
		try {
			List<DocumentTypeHasDocumentPropertyType> results = (List<DocumentTypeHasDocumentPropertyType>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding DocumentTypeHasDocumentPropertyType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DocumentTypeHasDocumentPropertyType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all DocumentTypeHasDocumentPropertyType instances");
		try {
			String queryString = "from DocumentTypeHasDocumentPropertyType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DocumentTypeHasDocumentPropertyType merge(
			DocumentTypeHasDocumentPropertyType detachedInstance) {
		log.debug("merging DocumentTypeHasDocumentPropertyType instance");
		try {
			DocumentTypeHasDocumentPropertyType result = (DocumentTypeHasDocumentPropertyType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DocumentTypeHasDocumentPropertyType instance) {
		log.debug("attaching dirty DocumentTypeHasDocumentPropertyType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocumentTypeHasDocumentPropertyType instance) {
		log.debug("attaching clean DocumentTypeHasDocumentPropertyType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocumentTypeHasDocumentPropertyTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DocumentTypeHasDocumentPropertyTypeDAO) ctx
				.getBean("DocumentTypeHasDocumentPropertyTypeDAO");
	}
}