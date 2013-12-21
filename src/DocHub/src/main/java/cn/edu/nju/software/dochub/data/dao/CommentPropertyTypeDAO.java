package cn.edu.nju.software.dochub.data.dao;

import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.nju.software.dochub.data.dataobject.CommentPropertyType;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommentPropertyType entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.edu.nju.software.dochub.data.dataobject.CommentPropertyType
 * @author MyEclipse Persistence Tools
 */

public class CommentPropertyTypeDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(CommentPropertyTypeDAO.class);
	// property constants
	public static final String NAME = "name";

	protected void initDao() {
		// do nothing
	}

	public void save(CommentPropertyType transientInstance) {
		log.debug("saving CommentPropertyType instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(CommentPropertyType persistentInstance) {
		log.debug("deleting CommentPropertyType instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public CommentPropertyType findById(java.lang.Integer id) {
		log.debug("getting CommentPropertyType instance with id: " + id);
		try {
			CommentPropertyType instance = (CommentPropertyType) getHibernateTemplate()
					.get("cn.edu.nju.software.dochub.data.dataobject.CommentPropertyType",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<CommentPropertyType> findByExample(CommentPropertyType instance) {
		log.debug("finding CommentPropertyType instance by example");
		try {
			List<CommentPropertyType> results = (List<CommentPropertyType>) getHibernateTemplate()
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
		log.debug("finding CommentPropertyType instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from CommentPropertyType as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<CommentPropertyType> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all CommentPropertyType instances");
		try {
			String queryString = "from CommentPropertyType";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public CommentPropertyType merge(CommentPropertyType detachedInstance) {
		log.debug("merging CommentPropertyType instance");
		try {
			CommentPropertyType result = (CommentPropertyType) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(CommentPropertyType instance) {
		log.debug("attaching dirty CommentPropertyType instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(CommentPropertyType instance) {
		log.debug("attaching clean CommentPropertyType instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CommentPropertyTypeDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (CommentPropertyTypeDAO) ctx.getBean("CommentPropertyTypeDAO");
	}
}