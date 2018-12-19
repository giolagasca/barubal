package com.accenture.tcf.bars.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.accenture.tcf.bars.domain.Request;

public class RequestDAOImpl implements IRequestDAO{
	private SessionFactory sessionFactory;
	
	public RequestDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertRequest(Request request) {
		Session session = sessionFactory.openSession();
		try {
	        session.beginTransaction();
	        session.save(request);
	        session.getTransaction().commit();
	    }
	    catch (HibernateException e) {
	       e.printStackTrace();
	       session.getTransaction().rollback();
	    }
		finally {
			session.close();
		}
	}

	@Override
    @SuppressWarnings("deprecation")
	public void deleteRequest() {
		Session session = sessionFactory.openSession();
		List<Request> list = new ArrayList<Request>();
		try {
	        session.beginTransaction();
			list = session.createCriteria(Request.class).list();
	        session.getTransaction().commit();
	    }
	    catch (HibernateException e) {
	       e.printStackTrace();
	       session.getTransaction().rollback();
	    }
		finally {
			session.close();
		}
		for(Request request : list) {
			session = sessionFactory.openSession();
			try {
				session.beginTransaction();
	        	session.delete(request);
		        session.getTransaction().commit();
			}
			catch (HibernateException e) {
		       e.printStackTrace();
		       session.getTransaction().rollback();
		    }
			finally {
				session.close();
			}
        }
	}
	
}
