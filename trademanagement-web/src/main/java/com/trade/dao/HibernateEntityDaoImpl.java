package com.trade.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HibernateEntityDaoImpl<T> implements HibernateEntityDao<T>{

	private Class<T> model;

	@Autowired
	private SessionFactory sessionFactory;
	
	public HibernateEntityDaoImpl(){
		
	}

	public HibernateEntityDaoImpl(Class<T> model){
		this.model = model;
	}
	
	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	@Override
	public void add(T t) throws HibernateException{
		currentSession().save(t);
	}

	@Override
	public void remove(T t,int id) throws HibernateException{		
		T tn = getById(t,id); 
		currentSession().delete(tn);		
	}

	@Override
	public void update(T t) throws HibernateException{
		currentSession().update(t);
	}

	@Override
	public List<T> getDataList(T t)throws HibernateException {
		return currentSession().createCriteria(t.getClass()).list();
	}

	@Override
	public T getById(T t, int id)throws HibernateException {
		return (T) currentSession().get(t.getClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByName(T t, String columnName, Object objColumnValue) throws HibernateException {
		
		StringBuilder builder = new StringBuilder();
		builder.append("FROM "+t.getClass().getSimpleName());
		builder.append(" as t WHERE "+columnName+"= :columnValue");
		Query query = currentSession().createQuery(builder.toString());
		if(objColumnValue instanceof Integer ){
			query.setInteger("columnValue",((Integer) objColumnValue).intValue());
		}else if(objColumnValue instanceof String){
			query.setParameter("columnValue", objColumnValue);
		}
		return query.list();
	}
	
	@Override
	public int deleteQuery(String qry, Object... params) throws HibernateException {
		Query query = currentSession().createQuery(qry);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByLikeName(T t, String columnName, Object columnValue)
			throws HibernateException {
		Query query = null;
		try{
			StringBuilder builder = new StringBuilder();
			builder.append("FROM "+t.getClass().getSimpleName());
			builder.append(" as t WHERE t."+columnName+" LIKE :columnValue");
			query = currentSession().createQuery(builder.toString()).setParameter("columnValue", columnValue+"%");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return query.list();
	}
}
