package cn.edu.nju.software.dochub.data.dao;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.nju.software.dochub.data.dataobject.Tagging;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tagging entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.edu.nju.software.dochub.data.dataobject.Tagging
 * @author MyEclipse Persistence Tools
 */

public class TaggingDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TaggingDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Tagging transientInstance) {
		log.debug("saving Tagging instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tagging persistentInstance) {
		log.debug("deleting Tagging instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tagging findById(java.lang.Integer id) {
		log.debug("getting Tagging instance with id: " + id);
		try {
			Tagging instance = (Tagging) getHibernateTemplate().get(
					"cn.edu.nju.software.dochub.data.dataobject.Tagging", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Tagging> findByExample(Tagging instance) {
		log.debug("finding Tagging instance by example");
		try {
			List<Tagging> results = (List<Tagging>) getHibernateTemplate()
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
		log.debug("finding Tagging instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tagging as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Tagging instances");
		try {
			String queryString = "from Tagging";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tagging merge(Tagging detachedInstance) {
		log.debug("merging Tagging instance");
		try {
			Tagging result = (Tagging) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tagging instance) {
		log.debug("attaching dirty Tagging instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tagging instance) {
		log.debug("attaching clean Tagging instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TaggingDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TaggingDAO) ctx.getBean("TaggingDAO");
	}
}