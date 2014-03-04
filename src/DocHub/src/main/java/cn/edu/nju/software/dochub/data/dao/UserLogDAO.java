package cn.edu.nju.software.dochub.data.dao;

import cn.edu.nju.software.dochub.data.dataobject.UserLog;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserLog entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see cn.edu.nju.software.dochub.data.dataobject.UserLog
 */

public class UserLogDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory.getLogger(UserLogDAO.class);
    // property constants
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String DESCRIPTION = "description";

    protected void initDao() {
        // do nothing
    }

    public void save(UserLog transientInstance) {
        log.debug("saving UserLog instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(UserLog persistentInstance) {
        log.debug("deleting UserLog instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public UserLog findById(java.lang.Integer id) {
        log.debug("getting UserLog instance with id: " + id);
        try {
            UserLog instance = (UserLog) getHibernateTemplate().get(
                    "cn.edu.nju.software.dochub.data.dataobject.UserLog", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<UserLog> findByExample(UserLog instance) {
        log.debug("finding UserLog instance by example");
        try {
            List<UserLog> results = (List<UserLog>) getHibernateTemplate()
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
        log.debug("finding UserLog instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from UserLog as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<UserLog> findByName(Object name) {
        return findByProperty(NAME, name);
    }

    public List<UserLog> findByType(Object type) {
        return findByProperty(TYPE, type);
    }

    public List<UserLog> findByDescription(Object description) {
        return findByProperty(DESCRIPTION, description);
    }

    public List findAll() {
        log.debug("finding all UserLog instances");
        try {
            String queryString = "from UserLog";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public UserLog merge(UserLog detachedInstance) {
        log.debug("merging UserLog instance");
        try {
            UserLog result = (UserLog) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(UserLog instance) {
        log.debug("attaching dirty UserLog instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(UserLog instance) {
        log.debug("attaching clean UserLog instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static UserLogDAO getFromApplicationContext(ApplicationContext ctx) {
        return (UserLogDAO) ctx.getBean("UserLogDAO");
    }
}