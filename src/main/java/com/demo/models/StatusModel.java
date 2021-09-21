package com.demo.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.entity.Accountstruts;
import com.demo.entity.Status;

public class StatusModel {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public Map<Integer, String> findAll() {
		Map<Integer, String> statusList = new HashMap<Integer, String>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			List<Status> list = session.createQuery("from Status").getResultList();
			for(Status item: list ) {
				statusList.put(item.getId(), item.getName());
			}			
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			statusList = null;
		} finally {
			session.close();
		}
		return statusList;
	}
	
	public Status find(int id) {
		Status status = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			status = (Status) session.createQuery("from Status where id = :id")
					.setParameter("id", id).getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			return status;
		} finally {
			session.close();
		}
		return status;
	}

}
