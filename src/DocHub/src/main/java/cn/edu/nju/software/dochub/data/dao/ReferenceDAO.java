package cn.edu.nju.software.dochub.data.dao;

import java.util.List;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.nju.software.dochub.data.dataobject.Reference;

/**
 * A data access object (DAO) providing persistence and search support for
 * Reference entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.edu.nju.software.dochub.data.dataobject.Reference
 * @author MyEclipse Persistence Tools
 */

public class ReferenceDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(ReferenceDAO.class);

	// property constants

	protected void initDao() {
		// do nothing
	}

	public void save(Reference transientInstance) {
		log.debug("saving Reference instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Reference persistentInstance) {
		log.debug("deleting Reference instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Reference findById(java.lang.Integer id) {
		log.debug("getting Reference instance with id: " + id);
		try {
			Reference instance = (Reference) getHibernateTemplate().get(
					"cn.edu.nju.software.dochub.data.Reference", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Reference> findByExample(Reference instance) {
		log.debug("finding Reference instance by example");
		try {
			List<Reference> results = (List<Reference>) getHibernateTemplate()
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
		log.debug("finding Reference instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Reference as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Reference instances");
		try {
			String queryString = "from Reference";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Reference merge(Reference detachedInstance) {
		log.debug("merging Reference instance");
		try {
			Reference result = (Reference) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Reference instance) {
		log.debug("attaching dirty Reference instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Reference instance) {
		log.debug("attaching clean Reference instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ReferenceDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ReferenceDAO) ctx.getBean("ReferenceDAO");
	}
}