package org.traderjournal.model.dao;

// Generated 06 Apr 2011 8:01:12 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.traderjournal.model.gen.Trade;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Trade.
 * @see org.traderjournal.model.gen.Trade
 * @author Hibernate Tools
 */
public class TradeHome {

	private static final Logger log = Logger.getLogger(Trade.class.getName());

	////////////// FINDERS CODE STARTS /////////////////

	public static final List<Trade> findAll(Session _session) {
		Query query = _session.createQuery("from Trade as Trade ");

		return (List<Trade>) query.list();
	}

	////////////// FINDERS CODE ENDS /////////////////

	public static void persist(Session _session, Trade transientInstance) {
		log.debug("persisting Trade instance");
		try {
			_session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public static void attachDirty(Session _session, Trade instance) {
		log.debug("attaching dirty Trade instance");
		try {
			_session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void attachClean(Session _session, Trade instance) {
		log.debug("attaching clean Trade instance");
		try {
			_session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void delete(Session _session, Trade persistentInstance) {
		log.debug("deleting Trade instance");
		try {
			_session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public static Trade merge(Session _session, Trade detachedInstance) {
		log.debug("merging Trade instance");
		try {
			Trade result = (Trade) _session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static Trade findById(Session _session, java.lang.Integer id) {
		log.debug("getting Trade instance with id: " + id);
		try {
			Trade instance = (Trade) _session.get(
					"org.traderjournal.model.gen.Trade", id);
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

	public static List<Trade> findByExample(Session _session, Trade instance) {
		log.debug("finding Trade instance by example");
		try {
			List<Trade> results = (List<Trade>) _session
					.createCriteria("org.traderjournal.model.gen.Trade")
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
