package org.traderjournal.model.dao;

// Generated 06 Apr 2011 8:01:12 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.traderjournal.model.gen.TransactionType;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class TransactionType.
 * @see org.traderjournal.model.gen.TransactionType
 * @author Hibernate Tools
 */
public class TransactionTypeHome {

	private static final Logger log = Logger.getLogger(TransactionType.class
			.getName());

	////////////// FINDERS CODE STARTS /////////////////

	public static final List<TransactionType> findAll(Session _session) {
		Query query = _session
				.createQuery("from TransactionType as TransactionType ");

		return (List<TransactionType>) query.list();
	}

	////////////// FINDERS CODE ENDS /////////////////

	public static void persist(Session _session,
			TransactionType transientInstance) {
		log.debug("persisting TransactionType instance");
		try {
			_session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public static void attachDirty(Session _session, TransactionType instance) {
		log.debug("attaching dirty TransactionType instance");
		try {
			_session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void attachClean(Session _session, TransactionType instance) {
		log.debug("attaching clean TransactionType instance");
		try {
			_session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void delete(Session _session,
			TransactionType persistentInstance) {
		log.debug("deleting TransactionType instance");
		try {
			_session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public static TransactionType merge(Session _session,
			TransactionType detachedInstance) {
		log.debug("merging TransactionType instance");
		try {
			TransactionType result = (TransactionType) _session
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static TransactionType findById(Session _session,
			java.lang.Integer id) {
		log.debug("getting TransactionType instance with id: " + id);
		try {
			TransactionType instance = (TransactionType) _session.get(
					"org.traderjournal.model.gen.TransactionType", id);
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

	public static List<TransactionType> findByExample(Session _session,
			TransactionType instance) {
		log.debug("finding TransactionType instance by example");
		try {
			List<TransactionType> results = (List<TransactionType>) _session
					.createCriteria(
							"org.traderjournal.model.gen.TransactionType")
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
