package cn.edu.nju.software.dochub.data.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.nju.software.dochub.data.dataobject.DocumentProperty;

/**
 * A data access object (DAO) providing persistence and search support for
 * DocumentProperty entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.edu.nju.software.dochub.data.dataobject.DocumentProperty
 * @author MyEclipse Persistence Tools
 */

public class DocumentPropertyDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DocumentPropertyDAO.class);
	// property constants
	public static final String VALUE = "value";

	protected void initDao() {
		// do nothing
	}

	public void save(DocumentProperty transientInstance) {
		log.debug("saving DocumentProperty instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DocumentProperty persistentInstance) {
		log.debug("deleting DocumentProperty instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DocumentProperty findById(java.lang.Integer id) {
		log.debug("getting DocumentProperty instance with id: " + id);
		try {
			DocumentProperty instance = (DocumentProperty) getHibernateTemplate()
					.get("cn.edu.nju.software.dochub.data.DocumentProperty", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DocumentProperty> findByExample(DocumentProperty instance) {
		log.debug("finding DocumentProperty instance by example");
		try {
			List<DocumentProperty> results = (List<DocumentProperty>) getHibernateTemplate()
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
		log.debug("finding DocumentProperty instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from DocumentProperty as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<DocumentProperty> findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findAll() {
		log.debug("finding all DocumentProperty instances");
		try {
			String queryString = "from DocumentProperty";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DocumentProperty merge(DocumentProperty detachedInstance) {
		log.debug("merging DocumentProperty instance");
		try {
			DocumentProperty result = (DocumentProperty) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DocumentProperty instance) {
		log.debug("attaching dirty DocumentProperty instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DocumentProperty instance) {
		log.debug("attaching clean DocumentProperty instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocumentPropertyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (DocumentPropertyDAO) ctx.getBean("DocumentPropertyDAO");
	}
}