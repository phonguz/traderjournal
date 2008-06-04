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
 * Home object for domain model class TransactionType.
 * @see traderjournal.model.hibernate.TransactionType
 * @author Hibernate Tools
 */

public class TransactionTypeHome {

	private static final Log log = LogFactory.getLog(TransactionTypeHome.class);
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

	public void persist(TransactionType transientInstance) {
		log.debug("persisting TransactionType instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(TransactionType instance) {
		log.debug("attaching dirty TransactionType instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TransactionType instance) {
		log.debug("attaching clean TransactionType instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(TransactionType persistentInstance) {
		log.debug("deleting TransactionType instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TransactionType merge(TransactionType detachedInstance) {
		log.debug("merging TransactionType instance");
		try {
			TransactionType result = (TransactionType) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List<TransactionType> findAll() {
		log.debug("getting all TransactionType instance");
		try {
			startOperation();
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from " + "traderjournal.model.hibernate.TransactionType");
			List<TransactionType> ret = (List<TransactionType>) query.list();
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

	public TransactionType findById(int id) {
		log.debug("getting TransactionType instance with id: " + id);
		try {
			startOperation();
			TransactionType instance = (TransactionType) sessionFactory
					.getCurrentSession()
					.get("traderjournal.model.hibernate.TransactionType", id);
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

	public List<TransactionType> findByExample(TransactionType instance) {
		log.debug("finding TransactionType instance by example");
		try {
			startOperation();
			List<TransactionType> results = (List<TransactionType>) sessionFactory
					.getCurrentSession().createCriteria(
							"traderjournal.model.hibernate.TransactionType")
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
