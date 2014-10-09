package org.traderjournal.model.dao;

// Generated 06 Apr 2011 8:01:12 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.traderjournal.model.gen.Instrument;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Instrument.
 * @see org.traderjournal.model.gen.Instrument
 * @author Hibernate Tools
 */
public class InstrumentHome {

	private static final Logger log = Logger.getLogger(Instrument.class
			.getName());

	////////////// FINDERS CODE STARTS /////////////////

	public static final List<Instrument> findAll(Session _session) {
		Query query = _session.createQuery("from Instrument as Instrument ");

		return (List<Instrument>) query.list();
	}

	////////////// FINDERS CODE ENDS /////////////////

	public static void persist(Session _session, Instrument transientInstance) {
		log.debug("persisting Instrument instance");
		try {
			_session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public static void attachDirty(Session _session, Instrument instance) {
		log.debug("attaching dirty Instrument instance");
		try {
			_session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void attachClean(Session _session, Instrument instance) {
		log.debug("attaching clean Instrument instance");
		try {
			_session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void delete(Session _session, Instrument persistentInstance) {
		log.debug("deleting Instrument instance");
		try {
			_session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public static Instrument merge(Session _session, Instrument detachedInstance) {
		log.debug("merging Instrument instance");
		try {
			Instrument result = (Instrument) _session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static Instrument findById(Session _session, java.lang.Integer id) {
		log.debug("getting Instrument instance with id: " + id);
		try {
			Instrument instance = (Instrument) _session.get(
					"org.traderjournal.model.gen.Instrument", id);
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

	public static List<Instrument> findByExample(Session _session,
			Instrument instance) {
		log.debug("finding Instrument instance by example");
		try {
			List<Instrument> results = (List<Instrument>) _session
					.createCriteria("org.traderjournal.model.gen.Instrument")
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
