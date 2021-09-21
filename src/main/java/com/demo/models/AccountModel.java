package com.demo.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;

import com.demo.entity.Accountstruts;

public class AccountModel {
	private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public Accountstruts create(Accountstruts account) {
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			session.save(account);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			account = null;
		} finally {
			session.close();
		}
		return account;
	}

	public boolean login(String username, String password) {
		boolean status = false;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			String passwordAccount = (String) session
					.createQuery("select password from Accountstruts where username = :username")
					.setParameter("username", username).getSingleResult();
			if (passwordAccount.isEmpty())
				status = false;
			if (BCrypt.checkpw(password, passwordAccount))
				status = true;
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

	public Accountstruts find(String username) {
		Accountstruts accountstruts = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = sessionFactory.openSession();
			transaction = session.beginTransaction();
			accountstruts = (Accountstruts) session.createQuery("from Accountstruts where username = :username")
					.setParameter("username", username).getSingleResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			return accountstruts;
		} finally {
			session.close();
		}
		return accountstruts;
	}

}
