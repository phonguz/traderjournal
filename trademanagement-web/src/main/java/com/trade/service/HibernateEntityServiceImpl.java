package com.trade.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trade.dao.HibernateEntityDao;

@Service
@Transactional
public class HibernateEntityServiceImpl<T> implements HibernateEntityService<T>{
	
	@Autowired
	HibernateEntityDao<T> hibernateEntityDao;

	@Override
	public void add(T t) throws HibernateException {
		hibernateEntityDao.add(t);
	}

	@Override
	public void remove(T t,int id) throws HibernateException {
		hibernateEntityDao.remove(t,id);
	}

	@Override
	public void update(T t) throws HibernateException {
		hibernateEntityDao.update(t);
	}

	@Override
	public List<T> getDataList(T t) throws HibernateException {
		return hibernateEntityDao.getDataList(t);
	}

	@Override
	public T getById(T t, int id) throws HibernateException {
		return hibernateEntityDao.getById(t, id);
	}

	@Override
	public List<T> getByName(T t, String columnName, Object columnValue)
			throws HibernateException {
		return hibernateEntityDao.getByName(t, columnName, columnValue);
	}

	@Override
	public int deleteQuery(String qry, Object... params)
			throws HibernateException {
		return hibernateEntityDao.deleteQuery(qry, params);
	}

	@Override
	public List<T> getByLikeName(T t, String columnName, Object columnValue)
			throws HibernateException {
		return hibernateEntityDao.getByLikeName(t, columnName, columnValue);
	}
}
