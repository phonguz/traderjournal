package org.traderjournal.model.dao;

// Generated 06 Apr 2011 8:01:12 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.traderjournal.model.gen.Transaction;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Transaction.
 * @see org.traderjournal.model.gen.Transaction
 * @author Hibernate Tools
 */
public class TransactionHome {

	private static final Logger log = Logger.getLogger(Transaction.class
			.getName());

	////////////// FINDERS CODE STARTS /////////////////

	public static final List<Transaction> findAll(Session _session) {
		Query query = _session.createQuery("from Transaction as Transaction ");

		return (List<Transaction>) query.list();
	}

	////////////// FINDERS CODE ENDS /////////////////

	public static void persist(Session _session, Transaction transientInstance) {
		log.debug("persisting Transaction instance");
		try {
			_session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public static void attachDirty(Session _session, Transaction instance) {
		log.debug("attaching dirty Transaction instance");
		try {
			_session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void attachClean(Session _session, Transaction instance) {
		log.debug("attaching clean Transaction instance");
		try {
			_session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void delete(Session _session, Transaction persistentInstance) {
		log.debug("deleting Transaction instance");
		try {
			_session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public static Transaction merge(Session _session,
			Transaction detachedInstance) {
		log.debug("merging Transaction instance");
		try {
			Transaction result = (Transaction) _session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static Transaction findById(Session _session, java.lang.Integer id) {
		log.debug("getting Transaction instance with id: " + id);
		try {
			Transaction instance = (Transaction) _session.get(
					"org.traderjournal.model.gen.Transaction", id);
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

	public static List<Transaction> findByExample(Session _session,
			Transaction instance) {
		log.debug("finding Transaction instance by example");
		try {
			List<Transaction> results = (List<Transaction>) _session
					.createCriteria("org.traderjournal.model.gen.Transaction")
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
