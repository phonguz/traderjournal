package traderjournal.model.hibernate;

// Generated 2008/06/05 03:03:08 by Hibernate Tools 3.2.1.GA

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
 * Home object for domain model class Account.
 * @see traderjournal.model.hibernate.Account
 * @author Hibernate Tools
 */

public class AccountHome {

	private static final Log log = LogFactory.getLog(AccountHome.class);
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

	public void persist(Account transientInstance) {
		log.debug("persisting Account instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Account instance) {
		log.debug("attaching dirty Account instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Account instance) {
		log.debug("attaching clean Account instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Account persistentInstance) {
		log.debug("deleting Account instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Account merge(Account detachedInstance) {
		log.debug("merging Account instance");
		try {
			Account result = (Account) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List<Account> findAll() {
		log.debug("getting all Account instance");
		try {
			startOperation();
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from " + "traderjournal.model.hibernate.Account");
			List<Account> ret = (List<Account>) query.list();
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

	public Account findById(int id) {
		log.debug("getting Account instance with id: " + id);
		try {
			startOperation();
			Account instance = (Account) sessionFactory.getCurrentSession()
					.get("traderjournal.model.hibernate.Account", id);
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

	public List<Account> findByExample(Account instance) {
		log.debug("finding Account instance by example");
		try {
			startOperation();
			List<Account> results = (List<Account>) sessionFactory
					.getCurrentSession().createCriteria(
							"traderjournal.model.hibernate.Account").add(
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
