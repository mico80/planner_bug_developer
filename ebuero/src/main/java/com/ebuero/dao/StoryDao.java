package com.ebuero.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ebuero.model.Story;


@Repository
@Transactional(readOnly=true)
public class StoryDao extends MainDao{
	
	@SuppressWarnings("unchecked")
	public List<Story> getAll(boolean onlyActive, boolean onlyEstimated) {
		Criteria criteria = getSession().createCriteria(Story.class);
		if(onlyActive) criteria.add(Restrictions.ne("status", Story.STATUS_COMPLETED));
		if(onlyEstimated) criteria.add(Restrictions.eq("status", Story.STATUS_ESTIMATED));
		criteria.addOrder(Order.asc("creationDate"));
		return criteria.list();
	}
	
	public Story getById(int id) {
		return (Story) getSession().get(Story.class, id);
	}
	
	@Transactional(readOnly=false)
	public int save(Story story)
	{
		return (Integer) getSession().save(story);
	}
	
	@Transactional(readOnly=false)
	public void update(Story story) {
		getSession().update(story);
	}

}
