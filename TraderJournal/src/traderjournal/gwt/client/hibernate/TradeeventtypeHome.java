package traderjournal.model.hibernate;

// Generated 2008/06/04 10:10:57 by Hibernate Tools 3.2.1.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import static org.hibernate.criterion.Example.create;
import traderjournal.model.DBUtils;

/**
 * Home object for domain model class Tradeeventtype.
 * @see traderjournal.model.hibernate.Tradeeventtype
 * @author Hibernate Tools
 */

public class TradeeventtypeHome {

	private static final Log log = LogFactory.getLog(TradeeventtypeHome.class);
	private Transaction tx;
	private final SessionFactory sessionFactory = getSessionFactory();

	public SessionFactory getSessionFactory() {
		try {
			return DBUtils.getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tradeeventtype transientInstance) {
		log.debug("persisting Tradeeventtype instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tradeeventtype instance) {
		log.debug("attaching dirty Tradeeventtype instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tradeeventtype instance) {
		log.debug("attaching clean Tradeeventtype instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tradeeventtype persistentInstance) {
		log.debug("deleting Tradeeventtype instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tradeeventtype merge(Tradeeventtype detachedInstance) {
		log.debug("merging Tradeeventtype instance");
		try {
			Tradeeventtype result = (Tradeeventtype) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List<Tradeeventtype> findAll() {
		log.debug("getting all Tradeeventtype instance");
		try {
			startOperation();
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from " + "traderjournal.model.hibernate.Tradeeventtype");
			List<Tradeeventtype> ret = (List<Tradeeventtype>) query.list();
			tx.commit();
			return ret;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	protected void startOperation() {
		tx = sessionFactory.getCurrentSession().beginTransaction();
	}

	public Tradeeventtype findById(int id) {
		log.debug("getting Tradeeventtype instance with id: " + id);
		try {
			startOperation();
			Tradeeventtype instance = (Tradeeventtype) sessionFactory
					.getCurrentSession().get(
							"traderjournal.model.hibernate.Tradeeventtype", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			tx.commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Tradeeventtype> findByExample(Tradeeventtype instance) {
		log.debug("finding Tradeeventtype instance by example");
		try {
			startOperation();
			List<Tradeeventtype> results = (List<Tradeeventtype>) sessionFactory
					.getCurrentSession().createCriteria(
							"traderjournal.model.hibernate.Tradeeventtype")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			tx.commit();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
