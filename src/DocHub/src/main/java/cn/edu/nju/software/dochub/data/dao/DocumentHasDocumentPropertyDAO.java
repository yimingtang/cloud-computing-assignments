package cn.edu.nju.software.dochub.data.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.nju.software.dochub.data.dataobject.DocumentHasDocumentProperty;

/**
 * A data access object (DAO) providing persistence and search support for
 * DocumentHasDocumentProperty entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see cn.edu.nju.software.dochub.data.dataobject.DocumentHasDocumentProperty
 * @author MyEclipse Persistence Tools
 */

public class DocumentHasDocumentPropertyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DocumentHasDocumentPropertyDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(DocumentHasDocumentProperty transientInstance) {
		log.debug("saving DocumentHasDocumentProperty instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DocumentHasDocumentProperty persistentInstance) {
		log.debug("deleting DocumentHasDocumentProperty instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocumentHasDocumentProperty findById(java.lang.Integer id) {
		log.debug("getting DocumentHasDocumentProperty instance with id: " + id);
		try {
			DocumentHasDocumentProperty instance = (DocumentHasDocumentProperty) getHibernateTemplate()
					.get("cn.edu.nju.software.dochub.data.dataobject.DocumentHasDocumentProperty",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DocumentHasDocumentProperty> findByExample(
			DocumentHasDocumentProperty instance) {
		log.debug("finding DocumentHasDocumentProperty instance by example");
		try {
			List<DocumentHasDocumentProperty> results = (List<DocumentHasDocumentProperty>) getHibernateTemplate()
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
		log.debug("finding DocumentHasDocumentProperty instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DocumentHasDocumentProperty as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all DocumentHasDocumentProperty instances");
		try {
			String queryString = "from DocumentHasDocumentProperty";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DocumentHasDocumentProperty merge(
			DocumentHasDocumentProperty detachedInstance) {
		log.debug("merging DocumentHasDocumentProperty instance");
		try {
			DocumentHasDocumentProperty result = (DocumentHasDocumentProperty) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DocumentHasDocumentProperty instance) {
		log.debug("attaching dirty DocumentHasDocumentProperty instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocumentHasDocumentProperty instance) {
		log.debug("attaching clean DocumentHasDocumentProperty instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocumentHasDocumentPropertyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DocumentHasDocumentPropertyDAO) ctx
				.getBean("DocumentHasDocumentPropertyDAO");
	}
}