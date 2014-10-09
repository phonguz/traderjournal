package org.traderjournal.model.dao;

// Generated 06 Apr 2011 8:01:12 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.traderjournal.model.gen.Tradeevent;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tradeevent.
 * @see org.traderjournal.model.gen.Tradeevent
 * @author Hibernate Tools
 */
public class TradeeventHome {

	private static final Logger log = Logger.getLogger(Tradeevent.class
			.getName());

	////////////// FINDERS CODE STARTS /////////////////

	public static final List<Tradeevent> findAll(Session _session) {
		Query query = _session.createQuery("from Tradeevent as Tradeevent ");

		return (List<Tradeevent>) query.list();
	}

	////////////// FINDERS CODE ENDS /////////////////

	public static void persist(Session _session, Tradeevent transientInstance) {
		log.debug("persisting Tradeevent instance");
		try {
			_session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public static void attachDirty(Session _session, Tradeevent instance) {
		log.debug("attaching dirty Tradeevent instance");
		try {
			_session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void attachClean(Session _session, Tradeevent instance) {
		log.debug("attaching clean Tradeevent instance");
		try {
			_session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void delete(Session _session, Tradeevent persistentInstance) {
		log.debug("deleting Tradeevent instance");
		try {
			_session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public static Tradeevent merge(Session _session, Tradeevent detachedInstance) {
		log.debug("merging Tradeevent instance");
		try {
			Tradeevent result = (Tradeevent) _session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static Tradeevent findById(Session _session, java.lang.Integer id) {
		log.debug("getting Tradeevent instance with id: " + id);
		try {
			Tradeevent instance = (Tradeevent) _session.get(
					"org.traderjournal.model.gen.Tradeevent", id);
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

	public static List<Tradeevent> findByExample(Session _session,
			Tradeevent instance) {
		log.debug("finding Tradeevent instance by example");
		try {
			List<Tradeevent> results = (List<Tradeevent>) _session
					.createCriteria("org.traderjournal.model.gen.Tradeevent")
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
