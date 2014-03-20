package com.ebuero.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MainDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		Session sess = sessionFactory.getCurrentSession();
		return sess;
	}
}
