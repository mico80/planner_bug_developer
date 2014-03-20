package com.ebuero.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ebuero.model.Bug;


@Repository
@Transactional(readOnly=true)
public class BugDao extends MainDao{
	
	@SuppressWarnings("unchecked")
	public List<Bug> getAll(boolean onlyActive) {
		Criteria criteria = getSession().createCriteria(Bug.class);
		if(onlyActive) criteria.add(Restrictions.ne("status", Bug.STATUS_RESOLVED));
		criteria.addOrder(Order.asc("creationDate"));
		return criteria.list();
	}
	
	@Transactional(readOnly=false)
	public int save(Bug story)
	{
		return (Integer) getSession().save(story);
	}
	
	@Transactional(readOnly=false)
	public void update(Bug story) {
		getSession().update(story);
	}

}
