package cn.edu.nju.software.dochub.data.dao;

import cn.edu.nju.software.dochub.data.dataobject.Attachment;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * A data access object (DAO) providing persistence and search support for
 * Attachment entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 *
 * @author MyEclipse Persistence Tools
 * @see cn.edu.nju.software.dochub.data.dataobject.Attachment
 */

public class AttachmentDAO extends HibernateDaoSupport {
    private static final Logger log = LoggerFactory
            .getLogger(AttachmentDAO.class);
    // property constants
    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String TYPE = "type";

    protected void initDao() {
        // do nothing
    }

    public void save(Attachment transientInstance) {
        log.debug("saving Attachment instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Attachment persistentInstance) {
        log.debug("deleting Attachment instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Attachment findById(java.lang.Integer id) {
        log.debug("getting Attachment instance with id: " + id);
        try {
            Attachment instance = (Attachment) getHibernateTemplate()
                    .get("cn.edu.nju.software.dochub.data.dataobject.Attachment",
                            id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }

    public List<Attachment> findByExample(Attachment instance) {
        log.debug("finding Attachment instance by example");
        try {
            List<Attachment> results = (List<Attachment>) getHibernateTemplate()
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
        log.debug("finding Attachment instance with property: " + propertyName
                + ", value: " + value);
        try {
            String queryString = "from Attachment as model where model."
                    + propertyName + "= ?";
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List<Attachment> findByName(Object name) {
        return findByProperty(NAME, name);
    }

    public List<Attachment> findByUrl(Object url) {
        return findByProperty(URL, url);
    }

    public List<Attachment> findByType(Object type) {
        return findByProperty(TYPE, type);
    }

    public List findAll() {
        log.debug("finding all Attachment instances");
        try {
            String queryString = "from Attachment";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public List findAttachmentByDocumentIdAndAttachmentName(
            int docid, String attachmentname) {
        log.debug("finding all Attachment instances By document and attachment name");
        try {
            String queryString = "from Attachment as a where a.document.id = " + docid + " and a.name = '" + attachmentname + "'";
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re) {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Attachment merge(Attachment detachedInstance) {
        log.debug("merging Attachment instance");
        try {
            Attachment result = (Attachment) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Attachment instance) {
        log.debug("attaching dirty Attachment instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Attachment instance) {
        log.debug("attaching clean Attachment instance");
        try {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static AttachmentDAO getFromApplicationContext(ApplicationContext ctx) {
        return (AttachmentDAO) ctx.getBean("AttachmentDAO");
    }


}