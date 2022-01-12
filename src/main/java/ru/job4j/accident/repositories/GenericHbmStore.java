package ru.job4j.accident.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import ru.job4j.accident.repositories.rules.ConsumerEx;
import ru.job4j.accident.repositories.rules.FunctionEx;

import java.util.Collection;

public abstract class GenericHbmStore<K, V> {

	private static final Logger LOG = LoggerFactory.getLogger(GenericHbmStore.class);
	private final SessionFactory sf;

	public GenericHbmStore(LocalSessionFactoryBean sfb) {
		sf = sfb.getObject();
	}

	protected <T> T select(FunctionEx<Session, T> command) {
		T result = null;
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		boolean error = false;
		try {
			result = command.apply(session);
		} catch (Exception ex) {
			error = true;
			LOG.error("Ошибка чтения данных: ", ex);
		} finally {
			if (error) {
				tx.rollback();
			} else {
				tx.commit();
			}
			session.close();
		}
		return result;
	}

	protected boolean execute(ConsumerEx<Session> dmlCommand) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		boolean error = false;
		try {
			dmlCommand.accept(session);
		} catch (Exception ex) {
			error = true;
			LOG.error("Ошибка изменения данных: ", ex);
		} finally {
			if (error) {
				tx.rollback();
			} else {
				tx.commit();
			}
			session.close();
		}
		return !error;
	}

	public abstract Collection<V> findAll();

	public abstract V findById(K id);

	public abstract boolean save(V value);

	public abstract boolean deleteById(K id);
}
