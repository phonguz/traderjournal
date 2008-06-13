package traderjournal.model.hibernate;

// Generated 2008/06/12 11:43:14 by Hibernate Tools 3.2.2.GA

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
 * Home object for domain model class Tradeevent.
 * @see traderjournal.model.hibernate.Tradeevent
 * @author Hibernate Tools
 */

public class TradeeventHome {

	private static final Log log = LogFactory.getLog(TradeeventHome.class);
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

	public void persist(Tradeevent transientInstance) {
		log.debug("persisting Tradeevent instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tradeevent instance) {
		log.debug("attaching dirty Tradeevent instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tradeevent instance) {
		log.debug("attaching clean Tradeevent instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tradeevent persistentInstance) {
		log.debug("deleting Tradeevent instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tradeevent merge(Tradeevent detachedInstance) {
		log.debug("merging Tradeevent instance");
		try {
			Tradeevent result = (Tradeevent) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List<Tradeevent> findAll() {
		log.debug("getting all Tradeevent instance");
		try {
			startOperation();
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from " + "traderjournal.model.hibernate.Tradeevent");
			List<Tradeevent> ret = (List<Tradeevent>) query.list();
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

	public Tradeevent findById(int id) {
		log.debug("getting Tradeevent instance with id: " + id);
		try {
			startOperation();
			Tradeevent instance = (Tradeevent) sessionFactory
					.getCurrentSession().get(
							"traderjournal.model.hibernate.Tradeevent", id);
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

	public List<Tradeevent> findByExample(Tradeevent instance) {
		log.debug("finding Tradeevent instance by example");
		try {
			startOperation();
			List<Tradeevent> results = (List<Tradeevent>) sessionFactory
					.getCurrentSession().createCriteria(
							"traderjournal.model.hibernate.Tradeevent").add(
							create(instance)).list();
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
