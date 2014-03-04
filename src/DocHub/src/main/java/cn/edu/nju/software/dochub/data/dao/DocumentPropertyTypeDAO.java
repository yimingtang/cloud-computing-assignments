package cn.edu.nju.software.dochub.data.dao;

import cn.edu.nju.software.dochub.data.dataobject.DocumentPropertyType;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * DocumentPropertyType entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see cn.edu.nju.software.dochub.data.dataobject.DocumentPropertyType
 */

public class DocumentPropertyTypeDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory
            .getLogger(DocumentPropertyTypeDAO.class);
    // property constants
    public static final String NAME = "name";

    protected void initDao() {
        // do nothing
    }

    public void save(DocumentPropertyType transientInstance) {
        log.debug("saving DocumentPropertyType instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(DocumentPropertyType persistentInstance) {
        log.debug("deleting DocumentPropertyType instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public DocumentPropertyType findById(java.lang.Integer id) {
        log.debug("getting DocumentPropertyType instance with id: " + id);
        try {
            DocumentPropertyType instance = (DocumentPropertyType) getHibernateTemplate()
                    .get("cn.edu.nju.software.dochub.data.dataobject.DocumentPropertyType",
                            id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<DocumentPropertyType> findByExample(
            DocumentPropertyType instance) {
        log.debug("finding DocumentPropertyType instance by example");
        try {
            List<DocumentPropertyType> results = (List<DocumentPropertyType>) getHibernateTemplate()
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
        log.debug("finding DocumentPropertyType instance with property: "
                + propertyName + ", value: " + value);
        try {
            String queryString = "from DocumentPropertyType as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<DocumentPropertyType> findByName(Object name) {
        return findByProperty(NAME, name);
    }

    public List findAll() {
        log.debug("finding all DocumentPropertyType instances");
        try {
            String queryString = "from DocumentPropertyType";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public DocumentPropertyType merge(DocumentPropertyType detachedInstance) {
        log.debug("merging DocumentPropertyType instance");
        try {
            DocumentPropertyType result = (DocumentPropertyType) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(DocumentPropertyType instance) {
        log.debug("attaching dirty DocumentPropertyType instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(DocumentPropertyType instance) {
        log.debug("attaching clean DocumentPropertyType instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static DocumentPropertyTypeDAO getFromApplicationContext(
            ApplicationContext ctx) {
        return (DocumentPropertyTypeDAO) ctx.getBean("DocumentPropertyTypeDAO");
    }
}