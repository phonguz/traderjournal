package traderjournal.wap.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import traderjournal.wap.model.entities.Trader;



public abstract class RequestFactoryUtilsJpa {

  private static final Logger log = Logger.getLogger(RequestFactoryUtilsJpa.class.getName());

  public static EntityManager getEntityManager() {
    return EMF.get().createEntityManager();
  }



  /**
   * get person logged in
   * @return
   */
  public static Long getLoggedInUserId() {
    

    
    Trader  userData = find(Trader.class,0L);
    if (userData == null) {
      return null;
    }
    if (userData.getId() == null) {
      return null;
    }

    return userData.getId();
  }

  /**
   * find[class] by id
   *  Id is of base64 representation of the key
   * @param clazz
   * @param id
   * @return
   */
  public static <T> T find(Class<T> clazz, String id) {
    if (id == null) {
      return null;
    }
    
    EntityManager em = getEntityManager();
    try {
      T e = em.find(clazz, id);
      return e;
    } finally {
      em.close();
    }
  }

  /**
   * find object by key
   * 
   * @param clazz
   * @param key
   * @return
   */
  public static <T> T find(Class<T> clazz, Long key) {
    EntityManager em = getEntityManager();
    try {
      T e = em.find(clazz, key);
      return e;
    } finally {
      em.close();
    }
  }
  
  

  /**
   * find object by key
   * 
   * @param clazz
   * @param key
   * @return
   */
  public static <T> List<T> findAll(Class<T> clazz) {
    EntityManager em = getEntityManager();
    try {
        String q = clazz.getSimpleName();
        Query query = em.createQuery("select * from "  + q);
        List<T> li= query.getResultList();
        return li;
    	
      
    } finally {
      em.close();
    }
  }


  /**
   * persist object
   *  NOTE: be sure to increment version in o
   * @param o
   * @return
   */
  public static <T> T persist(T o) {
    if (o == null) {
      return null;
    }

    // Could be done via interface, but simpler to do it in parent method, b/c it doesn't expose the public increment on client side
    // o.incrementVersion();

    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(o);
      tx.commit();
    } catch (Exception e) {
      log.log(Level.SEVERE, "RequestFactoryUtils Error: persist(): this=" + o, e);
      e.printStackTrace();
      return null;
    } finally {
      if (tx.isActive()) {
        tx.rollback();
      }
      em.close();
    }

    return o;
  }

  /**
   * only remove if admin
   * @param o
   * @return
   */
  public static <T> boolean removeByAdminOnly(T o) {

log.log(Level.WARNING,"Admin user not deved yet");

    Boolean success = null;
    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.persist(o);
      tx.commit();
      success = true;
    } catch (Exception e) {
      log.log(Level.SEVERE, "RequestFactoryUtils.removeAdminKeyOnly() Error: remove(): o=" + o, e);
      e.printStackTrace();
    } finally {
      if (tx.isActive()) {
        tx.rollback();
      }
      em.close();
    }

    return success;
  }

  /**
   * remove object
   * @param o
   * @return
   */
  public static <T> boolean remove(T o) {

    Boolean success = null;
    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.remove(o);
      tx.commit();
      success = true;
    } catch (Exception e) {
      log.log(Level.SEVERE, "RequestFactoryUtils.removeAdminKeyOnly() Error: remove(): o=" + o, e);
      e.printStackTrace();
    } finally {
      if (tx.isActive()) {
        tx.rollback();
      }
      em.close();
    }

    return success;
  }

  public static <T> boolean remove(Class<T> clazz, Long key) {

    //Key key = KeyFactory.stringToKey(id);
    T o = find(clazz, key); 
   
    Boolean success = null;
    EntityManager em = getEntityManager();
    EntityTransaction tx = em.getTransaction();
    try {
      tx.begin();
      em.remove(o);
      tx.commit();
      success = true;
    } catch (Exception e) {
      log.log(Level.SEVERE, "RequestFactoryUtils.removeAdminKeyOnly() Error: remove(): o=" + o, e);
      e.printStackTrace();
    } finally {
      if (tx.isActive()) {
        tx.rollback();
      }
      em.close();
    }

    return success;
  }

  /**
   * query list
   * @param clazz
   * @param qfilter
   * @return
   */
  public static <T> List<T> findList(Class<T> clazz, List<Filter> filter, long rangeStart, long rangeEnd) {

    String qfilter = getFilter(filter); 

    EntityManager em = getEntityManager();
    try {
      javax.persistence.Query q = em.createQuery("select o from " + clazz.getSimpleName() + " o " + qfilter);
      q.setFirstResult((int) rangeStart);
      q.setMaxResults((int) rangeEnd);
      List<T> list = q.getResultList();
      // force to get all the employees
      list.size();
      return list;

    } catch (Exception e) {
      log.log(Level.SEVERE, "Error: " + clazz.getName() + ".findList(): qfilter=" + qfilter, e);
      e.printStackTrace();
    } finally {
      em.close();
    }
    return null;
  }

  private static String getFilter(List<Filter> filter) {
    if (filter == null || filter.size() == 0) {
      return "";
    }
    String s = " WHERE ";
    Iterator<Filter> itr = filter.iterator();
    int i=0;
    while(itr.hasNext()) {
      Filter e = itr.next();
      s += getFilterValues(e);
      if (i < filter.size() - 1) {
        s += " AND ";
      }
      i++;
    }
    return s;
  }

  private static String getFilterValues(Filter e) {
    String key = e.getPropertyName();
    String operator = getOperator(e);
    String value = getValue(e);
    String s = key + " " + operator + " " + value;
    return s;
  }

  private static String getValue(Filter e) {
    if (e.getValue() == null) {
      return "null";
    }

    String s = "";
    if (e.getValue() instanceof String) {
      String v = (String) e.getValue();
      s = "\"" + v + "\"";
    } else{
    	s = "" + e.getValue();
    	
    }
    return s;
  }

  private static String getOperator(Filter e) {
    String s = "";
    if (e.getOperator() == FilterOperator.EQUAL) {
      s = "=";
    }else if(e.getOperator() == FilterOperator.GREATERTHAN){
    	s = ">";
    	}
    return s;
  }

  /**
   * find list count
   * @param clazz
   * @param qfilter
   * @return
   */
  /*
  public static <T> Long findCount(Class<T> clazz, ArrayList<Filter> filter) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    try {
      String q = clazz.getSimpleName();
      Query query = new Query(q);
      query.setKeysOnly();
      if (filter != null) {
        Iterator<Filter> itr = filter.iterator();
        while (itr.hasNext()) {
          Filter f = itr.next();
          query.addFilter(f.getPropertyName(), f.getOperator(), f.getValue());
        }
      }
      PreparedQuery pq = datastore.prepare(query);

      long total = pq.countEntities(FetchOptions.Builder.withDefaults());
      return total;
    } catch (Exception e) {
      log.log(Level.SEVERE, "Error: " + clazz.getName() + ".findList(): ", e);
      e.printStackTrace();
    } 
    return null;
  }*/
}
