package traderjournal.model.hibernate;

// Generated 2009/09/11 03:55:58 by Hibernate Tools 3.2.5.Beta

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import traderjournal.model.DBUtils;

/**
 * Home object for domain model class Instrument.
 * @see traderjournal.model.hibernate.Instrument
 * @author Hibernate Tools
 */

public class InstrumentHome {

	private static final Log log = LogFactory.getLog(InstrumentHome.class);
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

	public void persist(Instrument transientInstance) {
		log.debug("persisting Instrument instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Instrument instance) {
		log.debug("attaching dirty Instrument instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Instrument instance) {
		log.debug("attaching clean Instrument instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Instrument persistentInstance) {
		log.debug("deleting Instrument instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Instrument merge(Instrument detachedInstance) {
		log.debug("merging Instrument instance");
		try {
			Instrument result = (Instrument) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List<Instrument> findAll() {
		log.debug("getting all Instrument instance");
		try {
			startOperation();
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from " + "traderjournal.model.hibernate.Instrument");
			List<Instrument> ret = (List<Instrument>) query.list();
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

	public Instrument findById(int id) {
		log.debug("getting Instrument instance with id: " + id);
		try {
			startOperation();
			Instrument instance = (Instrument) sessionFactory
					.getCurrentSession().get(
							"traderjournal.model.hibernate.Instrument", id);
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

	public List findByExample(Instrument instance) {
		log.debug("finding Instrument instance by example");
		try {
			startOperation();
			List results = sessionFactory.getCurrentSession().createCriteria(
					"traderjournal.model.hibernate.Instrument").add(
					Example.create(instance)).list();
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
