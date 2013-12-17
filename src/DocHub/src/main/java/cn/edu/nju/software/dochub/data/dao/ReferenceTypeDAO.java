package cn.edu.nju.software.dochub.data.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.nju.software.dochub.data.dataobject.ReferenceType;

/**
 * A data access object (DAO) providing persistence and search support for
 * ReferenceType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.edu.nju.software.dochub.data.dataobject.ReferenceType
 * @author MyEclipse Persistence Tools
 */

public class ReferenceTypeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ReferenceTypeDAO.class);
	// property constants
	public static final String NAME = "name";

	protected void initDao() {
		// do nothing
	}

	public void save(ReferenceType transientInstance) {
		log.debug("saving ReferenceType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ReferenceType persistentInstance) {
		log.debug("deleting ReferenceType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ReferenceType findById(java.lang.Integer id) {
		log.debug("getting ReferenceType instance with id: " + id);
		try {
			ReferenceType instance = (ReferenceType) getHibernateTemplate()
					.get("cn.edu.nju.software.dochub.data.ReferenceType", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<ReferenceType> findByExample(ReferenceType instance) {
		log.debug("finding ReferenceType instance by example");
		try {
			List<ReferenceType> results = (List<ReferenceType>) getHibernateTemplate()
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
		log.debug("finding ReferenceType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ReferenceType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<ReferenceType> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all ReferenceType instances");
		try {
			String queryString = "from ReferenceType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ReferenceType merge(ReferenceType detachedInstance) {
		log.debug("merging ReferenceType instance");
		try {
			ReferenceType result = (ReferenceType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ReferenceType instance) {
		log.debug("attaching dirty ReferenceType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ReferenceType instance) {
		log.debug("attaching clean ReferenceType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReferenceTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ReferenceTypeDAO) ctx.getBean("ReferenceTypeDAO");
	}
}