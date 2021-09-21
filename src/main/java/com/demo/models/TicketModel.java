package com.demo.models;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.entity.Accountstruts;
import com.demo.entity.Ticket;

public class TicketModel {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public List<Ticket> findAll(int id) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		Session session = null;
		Transaction transaction = null;		
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			tickets = session.createQuery("from Ticket where accountstruts.id = :id")
					.setParameter("id", id).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println(e);
			tickets = null;
		} finally {
			session.close();
		}
		return tickets;
	}

	public Ticket create(Ticket ticket) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(ticket);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println(e);
			ticket = null;
		} finally {
			session.close();
		}
		return ticket;
	}
	
	public Ticket find(int id) {
		Ticket ticket = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			ticket = (Ticket) session.createQuery("from Ticket where id = :id")
					.setParameter("id", id).getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			return ticket;
		} finally {
			session.close();
		}
		return ticket;
	}
	
	public Ticket delete(Ticket ticket) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.remove(ticket);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println(e);
			ticket = null;
		} finally {
			session.close();
		}
		return ticket;
	}	
}
