package com.demo.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.entity.Categorystruts;
import com.demo.entity.Status;

public class CategoryModel {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public Map<Integer, String> findAll() {
		Map<Integer, String> categories = new HashMap<Integer, String>();
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			List<Categorystruts> List = session.createQuery("from Categorystruts").getResultList();
			for(Categorystruts item: List ) {
				categories.put(item.getId(), item.getName());
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			categories = null;
		} finally {
			session.close();
		}
		return categories;
	}
	
	public Categorystruts find(int id) {
		Categorystruts categorystruts = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			categorystruts = (Categorystruts) session.createQuery("from Categorystruts where id = :id")
					.setParameter("id", id).getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			return categorystruts;
		} finally {
			session.close();
		}
		return categorystruts;
	}

}
