package org.traderjournal.model.dao;

// Generated 06 Apr 2011 8:01:12 AM by Hibernate Tools 3.4.0.CR1

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.traderjournal.model.gen.Tradeeventimage;

import static org.hibernate.criterion.Example.create;

/**
 * Home object for domain model class Tradeeventimage.
 * @see org.traderjournal.model.gen.Tradeeventimage
 * @author Hibernate Tools
 */
public class TradeeventimageHome {

	private static final Logger log = Logger.getLogger(Tradeeventimage.class
			.getName());

	////////////// FINDERS CODE STARTS /////////////////

	public static final List<Tradeeventimage> findAll(Session _session) {
		Query query = _session
				.createQuery("from Tradeeventimage as Tradeeventimage ");

		return (List<Tradeeventimage>) query.list();
	}

	////////////// FINDERS CODE ENDS /////////////////

	public static void persist(Session _session,
			Tradeeventimage transientInstance) {
		log.debug("persisting Tradeeventimage instance");
		try {
			_session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public static void attachDirty(Session _session, Tradeeventimage instance) {
		log.debug("attaching dirty Tradeeventimage instance");
		try {
			_session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void attachClean(Session _session, Tradeeventimage instance) {
		log.debug("attaching clean Tradeeventimage instance");
		try {
			_session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static void delete(Session _session,
			Tradeeventimage persistentInstance) {
		log.debug("deleting Tradeeventimage instance");
		try {
			_session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public static Tradeeventimage merge(Session _session,
			Tradeeventimage detachedInstance) {
		log.debug("merging Tradeeventimage instance");
		try {
			Tradeeventimage result = (Tradeeventimage) _session
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public static Tradeeventimage findById(Session _session,
			java.lang.Integer id) {
		log.debug("getting Tradeeventimage instance with id: " + id);
		try {
			Tradeeventimage instance = (Tradeeventimage) _session.get(
					"org.traderjournal.model.gen.Tradeeventimage", id);
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

	public static List<Tradeeventimage> findByExample(Session _session,
			Tradeeventimage instance) {
		log.debug("finding Tradeeventimage instance by example");
		try {
			List<Tradeeventimage> results = (List<Tradeeventimage>) _session
					.createCriteria(
							"org.traderjournal.model.gen.Tradeeventimage")
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
