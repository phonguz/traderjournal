package org.traderjournal.model.dao;

// Generated 06 Apr 2011 8:01:12 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.traderjournal.model.gen.Ccy;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Ccy.
 * @see org.traderjournal.model.gen.Ccy
 * @author Hibernate Tools
 */
public class CcyHome {

	private static final Logger log = Logger.getLogger(Ccy.class.getName());

	////////////// FINDERS CODE STARTS /////////////////

	public static final List<Ccy> findAll(Session _session) {
		Query query = _session.createQuery("from Ccy as Ccy ");

		return (List<Ccy>) query.list();
	}

	////////////// FINDERS CODE ENDS /////////////////

	public static void persist(Session _session, Ccy transientInstance) {
		log.debug("persisting Ccy instance");
		try {
			_session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public static void attachDirty(Session _session, Ccy instance) {
		log.debug("attaching dirty Ccy instance");
		try {
			_session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void attachClean(Session _session, Ccy instance) {
		log.debug("attaching clean Ccy instance");
		try {
			_session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void delete(Session _session, Ccy persistentInstance) {
		log.debug("deleting Ccy instance");
		try {
			_session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public static Ccy merge(Session _session, Ccy detachedInstance) {
		log.debug("merging Ccy instance");
		try {
			Ccy result = (Ccy) _session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static Ccy findById(Session _session, java.lang.Integer id) {
		log.debug("getting Ccy instance with id: " + id);
		try {
			Ccy instance = (Ccy) _session.get(
					"org.traderjournal.model.gen.Ccy", id);
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

	public static List<Ccy> findByExample(Session _session, Ccy instance) {
		log.debug("finding Ccy instance by example");
		try {
			List<Ccy> results = (List<Ccy>) _session
					.createCriteria("org.traderjournal.model.gen.Ccy")
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
