package com.trade.dao;

import java.util.List;

import org.hibernate.HibernateException;

public interface HibernateEntityDao<T> {
	
	public void add(T t) throws HibernateException;

	public void remove(T t,int id)throws HibernateException;

	public void update(T t)throws HibernateException;

	public List<T> getDataList(T t)throws HibernateException;

	public T getById(T t, int id)throws HibernateException;

	public List<T> getByName(T t, String columnName, Object columnValue) throws HibernateException;
	
	public int deleteQuery(String qry, Object... params) throws HibernateException;
	
	public List<T> getByLikeName(T t, String columnName, Object columnValue) throws HibernateException;
}
