package cn.edu.nju.software.dochub.data.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;
import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.edu.nju.software.dochub.data.dataobject.Document;

/**
 * A data access object (DAO) providing persistence and search support for
 * Document entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.edu.nju.software.dochub.data.dataobject.Document
 * @author MyEclipse Persistence Tools
 */

public class DocumentDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(DocumentDAO.class);
	// property constants
	public static final String TITLE = "title";
	public static final String AUTHOR = "author";
	public static final String PAGES = "pages";
	public static final String ABSTRACT_ = "abstract_";
	public static final String KEYWORDS = "keywords";
	public static final String PUBLISHER = "publisher";
	public static final String URL = "url";
	public static final String PUBLISHED = "published";

	protected void initDao() {
		// do nothing
	}

	public void save(Document transientInstance) {
		log.debug("saving Document instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Document persistentInstance) {
		log.debug("deleting Document instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Document findById(java.lang.Integer id) {
		log.debug("getting Document instance with id: " + id);
		try {
			Document instance = (Document) getHibernateTemplate().get(
					"cn.edu.nju.software.dochub.data.Document", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Document> findByExample(Document instance) {
		log.debug("finding Document instance by example");
		try {
			List<Document> results = (List<Document>) getHibernateTemplate()
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
		log.debug("finding Document instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Document as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Document> findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	public List<Document> findByAuthor(Object author) {
		return findByProperty(AUTHOR, author);
	}

	public List<Document> findByPages(Object pages) {
		return findByProperty(PAGES, pages);
	}

	public List<Document> findByAbstract_(Object abstract_) {
		return findByProperty(ABSTRACT_, abstract_);
	}

	public List<Document> findByKeywords(Object keywords) {
		return findByProperty(KEYWORDS, keywords);
	}

	public List<Document> findByPublisher(Object publisher) {
		return findByProperty(PUBLISHER, publisher);
	}

	public List<Document> findByUrl(Object url) {
		return findByProperty(URL, url);
	}

	public List<Document> findByPublished(Object published) {
		return findByProperty(PUBLISHED, published);
	}

	public List findAll() {
		log.debug("finding all Document instances");
		try {
			String queryString = "from Document";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Document merge(Document detachedInstance) {
		log.debug("merging Document instance");
		try {
			Document result = (Document) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Document instance) {
		log.debug("attaching dirty Document instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Document instance) {
		log.debug("attaching clean Document instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DocumentDAO getFromApplicationContext(ApplicationContext ctx) {
		return (DocumentDAO) ctx.getBean("DocumentDAO");
	}
}