package com.filocha.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionFactoryExtensions {

	public static <T> T run(SessionFactory factory, InTransactionOperation<T> instance) {
		Session session = factory.openSession();
		try {
			session.beginTransaction();
			try {
				T result = instance.run(session);
				session.getTransaction().commit();
				return result;
			} catch (Exception e) {
				session.getTransaction().rollback();
				throw e;
			}
		} finally {
			session.close();
		}
	}

	/**
	 * Represents operation which need to be run in transaction. The transaction
	 * is created before invocation and closed after invocation even when an
	 * exception occurs.
	 * 
	 */
	public interface InTransactionOperation<T> {
		T run(Session session);
	}
}
