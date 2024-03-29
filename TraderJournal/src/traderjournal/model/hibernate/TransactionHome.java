package traderjournal.model.hibernate;

// Generated 2008/06/12 11:43:14 by Hibernate Tools 3.2.2.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import traderjournal.model.DBUtils;

/**
 * Home object for domain model class Transaction.
 * @see traderjournal.model.hibernate.Transaction
 * @author Hibernate Tools
 */

public class TransactionHome {

	private static final Log log = LogFactory.getLog(TransactionHome.class);
	private org.hibernate.Transaction tx;
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

	public void persist(Transaction transientInstance) {
		log.debug("persisting Transaction instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Transaction instance) {
		log.debug("attaching dirty Transaction instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Transaction instance) {
		log.debug("attaching clean Transaction instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Transaction persistentInstance) {
		log.debug("deleting Transaction instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Transaction merge(Transaction detachedInstance) {
		log.debug("merging Transaction instance");
		try {
			Transaction result = (Transaction) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List<Transaction> findAll() {
		log.debug("getting all Transaction instance");
		try {
			startOperation();
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from " + "traderjournal.model.hibernate.Transaction");
			List<Transaction> ret = (List<Transaction>) query.list();
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

	public Transaction findById(int id) {
		log.debug("getting Transaction instance with id: " + id);
		try {
			startOperation();
			Transaction instance = (Transaction) sessionFactory
					.getCurrentSession().get(
							"traderjournal.model.hibernate.Transaction", id);
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

	public List<Transaction> findByExample(Transaction instance) {
		log.debug("finding Transaction instance by example");
		try {
			startOperation();
			List<Transaction> results = (List<Transaction>) sessionFactory
					.getCurrentSession().createCriteria(
							"traderjournal.model.hibernate.Transaction").add(
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
