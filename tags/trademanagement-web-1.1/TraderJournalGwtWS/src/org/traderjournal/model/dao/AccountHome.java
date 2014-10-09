package org.traderjournal.model.dao;

// Generated 06 Apr 2011 8:01:12 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.traderjournal.model.gen.Account;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Account.
 * @see org.traderjournal.model.gen.Account
 * @author Hibernate Tools
 */
public class AccountHome {

	private static final Logger log = Logger.getLogger(Account.class.getName());

	////////////// FINDERS CODE STARTS /////////////////

	public static final List<Account> findAll(Session _session) {
		Query query = _session.createQuery("from Account as Account ");

		return (List<Account>) query.list();
	}

	////////////// FINDERS CODE ENDS /////////////////

	public static void persist(Session _session, Account transientInstance) {
		log.debug("persisting Account instance");
		try {
			_session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public static void attachDirty(Session _session, Account instance) {
		log.debug("attaching dirty Account instance");
		try {
			_session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void attachClean(Session _session, Account instance) {
		log.debug("attaching clean Account instance");
		try {
			_session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void delete(Session _session, Account persistentInstance) {
		log.debug("deleting Account instance");
		try {
			_session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public static Account merge(Session _session, Account detachedInstance) {
		log.debug("merging Account instance");
		try {
			Account result = (Account) _session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static Account findById(Session _session, java.lang.Integer id) {
		log.debug("getting Account instance with id: " + id);
		try {
			Account instance = (Account) _session.get(
					"org.traderjournal.model.gen.Account", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public static List<Account> findByExample(Session _session, Account instance) {
		log.debug("finding Account instance by example");
		try {
			List<Account> results = (List<Account>) _session
					.createCriteria("org.traderjournal.model.gen.Account")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
