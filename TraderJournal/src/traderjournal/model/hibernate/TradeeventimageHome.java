package traderjournal.model.hibernate;

// Generated 2008/05/28 12:26:25 by Hibernate Tools 3.2.1.GA

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import static org.hibernate.criterion.Example.create;
import traderjournal.model.DBUtils;

/**
 * Home object for domain model class Tradeeventimage.
 * @see traderjournal.model.hibernate.Tradeeventimage
 * @author Hibernate Tools
 */
public class TradeeventimageHome {

	private static final Log log = LogFactory.getLog(TradeeventimageHome.class);
	private Transaction tx;
	private final SessionFactory sessionFactory = getSessionFactory();

	public SessionFactory getSessionFactory() {
		try {
			return DBUtils.getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Tradeeventimage transientInstance) {
		log.debug("persisting Tradeeventimage instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Tradeeventimage instance) {
		log.debug("attaching dirty Tradeeventimage instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tradeeventimage instance) {
		log.debug("attaching clean Tradeeventimage instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Tradeeventimage persistentInstance) {
		log.debug("deleting Tradeeventimage instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tradeeventimage merge(Tradeeventimage detachedInstance) {
		log.debug("merging Tradeeventimage instance");
		try {
			Tradeeventimage result = (Tradeeventimage) sessionFactory
					.getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public List<Tradeeventimage> findAll() {
		log.debug("getting all Tradeeventimage instance");
		try {
			startOperation();
			Query query = sessionFactory.getCurrentSession().createQuery(
					"from " + "traderjournal.model.hibernate.Tradeeventimage");
			List<Tradeeventimage> ret = (List<Tradeeventimage>) query.list();
			tx.commit();
			return ret;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	protected void startOperation() {
		tx = sessionFactory.getCurrentSession().beginTransaction();
	}

	public Tradeeventimage findById(int id) {
		log.debug("getting Tradeeventimage instance with id: " + id);
		try {
			startOperation();
			Tradeeventimage instance = (Tradeeventimage) sessionFactory
					.getCurrentSession()
					.get("traderjournal.model.hibernate.Tradeeventimage", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			tx.commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Tradeeventimage> findByExample(Tradeeventimage instance) {
		log.debug("finding Tradeeventimage instance by example");
		try {
			startOperation();
			List<Tradeeventimage> results = (List<Tradeeventimage>) sessionFactory
					.getCurrentSession().createCriteria(
							"traderjournal.model.hibernate.Tradeeventimage")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			tx.commit();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
}
