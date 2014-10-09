package org.traderjournal.model.dao;

// Generated 06 Apr 2011 8:01:12 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.traderjournal.model.gen.Trader;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Trader.
 * @see org.traderjournal.model.gen.Trader
 * @author Hibernate Tools
 */
public class TraderHome {

	private static final Logger log = Logger.getLogger(Trader.class.getName());

	////////////// FINDERS CODE STARTS /////////////////

	public static final List<Trader> findAll(Session _session) {
		Query query = _session.createQuery("from Trader as Trader ");

		return (List<Trader>) query.list();
	}

	////////////// FINDERS CODE ENDS /////////////////

	public static void persist(Session _session, Trader transientInstance) {
		log.debug("persisting Trader instance");
		try {
			_session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public static void attachDirty(Session _session, Trader instance) {
		log.debug("attaching dirty Trader instance");
		try {
			_session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void attachClean(Session _session, Trader instance) {
		log.debug("attaching clean Trader instance");
		try {
			_session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void delete(Session _session, Trader persistentInstance) {
		log.debug("deleting Trader instance");
		try {
			_session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public static Trader merge(Session _session, Trader detachedInstance) {
		log.debug("merging Trader instance");
		try {
			Trader result = (Trader) _session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static Trader findById(Session _session, java.lang.Integer id) {
		log.debug("getting Trader instance with id: " + id);
		try {
			Trader instance = (Trader) _session.get(
					"org.traderjournal.model.gen.Trader", id);
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

	public static List<Trader> findByExample(Session _session, Trader instance) {
		log.debug("finding Trader instance by example");
		try {
			List<Trader> results = (List<Trader>) _session
					.createCriteria("org.traderjournal.model.gen.Trader")
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
