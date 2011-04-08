package org.traderjournal.model.dao;

// Generated 06 Apr 2011 8:01:12 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.traderjournal.model.gen.Tradeeventtype;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tradeeventtype.
 * @see org.traderjournal.model.gen.Tradeeventtype
 * @author Hibernate Tools
 */
public class TradeeventtypeHome {

	private static final Logger log = Logger.getLogger(Tradeeventtype.class
			.getName());

	////////////// FINDERS CODE STARTS /////////////////

	public static final List<Tradeeventtype> findAll(Session _session) {
		Query query = _session
				.createQuery("from Tradeeventtype as Tradeeventtype ");

		return (List<Tradeeventtype>) query.list();
	}

	////////////// FINDERS CODE ENDS /////////////////

	public static void persist(Session _session,
			Tradeeventtype transientInstance) {
		log.debug("persisting Tradeeventtype instance");
		try {
			_session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public static void attachDirty(Session _session, Tradeeventtype instance) {
		log.debug("attaching dirty Tradeeventtype instance");
		try {
			_session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void attachClean(Session _session, Tradeeventtype instance) {
		log.debug("attaching clean Tradeeventtype instance");
		try {
			_session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void delete(Session _session,
			Tradeeventtype persistentInstance) {
		log.debug("deleting Tradeeventtype instance");
		try {
			_session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public static Tradeeventtype merge(Session _session,
			Tradeeventtype detachedInstance) {
		log.debug("merging Tradeeventtype instance");
		try {
			Tradeeventtype result = (Tradeeventtype) _session
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static Tradeeventtype findById(Session _session, java.lang.Integer id) {
		log.debug("getting Tradeeventtype instance with id: " + id);
		try {
			Tradeeventtype instance = (Tradeeventtype) _session.get(
					"org.traderjournal.model.gen.Tradeeventtype", id);
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

	public static List<Tradeeventtype> findByExample(Session _session,
			Tradeeventtype instance) {
		log.debug("finding Tradeeventtype instance by example");
		try {
			List<Tradeeventtype> results = (List<Tradeeventtype>) _session
					.createCriteria(
							"org.traderjournal.model.gen.Tradeeventtype")
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
