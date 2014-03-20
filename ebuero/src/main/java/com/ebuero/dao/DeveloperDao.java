package com.ebuero.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ebuero.model.Developer;


@Repository
@Transactional(readOnly=true)
public class DeveloperDao extends MainDao{
	
	@SuppressWarnings("unchecked")
	public List<Developer> getAll() {
		Criteria criteria = getSession().createCriteria(Developer.class);
		criteria.addOrder(Order.asc("name"));
		return criteria.list();
	}
	
	public int countDevelopers() {
		return ((Number) getSession().createCriteria(Developer.class).setProjection(Projections.rowCount()).uniqueResult()).intValue();
	}
	
	@Transactional(readOnly=false)
	public int save(Developer developer)
	{
		return (Integer) getSession().save(developer);
	}
	
	@Transactional(readOnly=false)
	public void delete(Developer item) {
		getSession().delete(item);
	}

}
