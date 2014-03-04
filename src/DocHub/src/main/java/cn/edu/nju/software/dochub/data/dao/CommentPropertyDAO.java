package cn.edu.nju.software.dochub.data.dao;

import cn.edu.nju.software.dochub.data.dataobject.CommentProperty;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * CommentProperty entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see cn.edu.nju.software.dochub.data.dataobject.CommentProperty
 */

public class CommentPropertyDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory
            .getLogger(CommentPropertyDAO.class);
    // property constants
    public static final String VALUE = "value";

    protected void initDao() {
        // do nothing
    }

    public void save(CommentProperty transientInstance) {
        log.debug("saving CommentProperty instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(CommentProperty persistentInstance) {
        log.debug("deleting CommentProperty instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public CommentProperty findById(java.lang.Integer id) {
        log.debug("getting CommentProperty instance with id: " + id);
        try {
            CommentProperty instance = (CommentProperty) getHibernateTemplate()
                    .get("cn.edu.nju.software.dochub.data.dataobject.CommentProperty",
                            id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<CommentProperty> findByExample(CommentProperty instance) {
        log.debug("finding CommentProperty instance by example");
        try {
            List<CommentProperty> results = (List<CommentProperty>) getHibernateTemplate()
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
        log.debug("finding CommentProperty instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from CommentProperty as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<CommentProperty> findByValue(Object value) {
        return findByProperty(VALUE, value);
    }

    public List findAll() {
        log.debug("finding all CommentProperty instances");
        try {
            String queryString = "from CommentProperty";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public CommentProperty merge(CommentProperty detachedInstance) {
        log.debug("merging CommentProperty instance");
        try {
            CommentProperty result = (CommentProperty) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(CommentProperty instance) {
        log.debug("attaching dirty CommentProperty instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(CommentProperty instance) {
        log.debug("attaching clean CommentProperty instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static CommentPropertyDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (CommentPropertyDAO) ctx.getBean("CommentPropertyDAO");
    }
}