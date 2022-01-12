package ru.job4j.accident.repositories;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Accident;
import ru.job4j.accident.repositories.rules.ConsumerEx;
import ru.job4j.accident.repositories.rules.FunctionEx;

import java.util.Collection;
import java.util.List;

@Repository
public class HbmAccidentRepository extends GenericHbmStore<Integer, Accident> {

	public HbmAccidentRepository(LocalSessionFactoryBean sfb) {
		super(sfb);
	}

	@Override
	public Collection<Accident> findAll() {
		FunctionEx<Session, List<Accident>> f = (s) -> {
			String hql =
				"FROM Accident a "
				+ "JOIN FETCH a.state "
				+ "LEFT JOIN FETCH a.type "
				+ "ORDER BY a.created DESC, a.state.id";
			Query q = s.createQuery(hql, Accident.class);
			return q.getResultList();
		};
		return select(f);
	}

	@Override
	public Accident findById(Integer id) {
		FunctionEx<Session, Accident> f = (s) -> {
			String hql =
				"SELECT DISTINCT a FROM Accident a "
				+ "LEFT JOIN FETCH a.type "
				+ "JOIN FETCH a.author "
				+ "LEFT JOIN FETCH a.inspector "
				+ "JOIN FETCH a.state "
				+ "LEFT JOIN FETCH a.rules "
				+ "LEFT JOIN FETCH a.attachments "
				+ "WHERE a.id = :fId";
			return
					s.createQuery(hql, Accident.class)
					.setParameter("fId", id)
					.getSingleResult();
		};
		return select(f);
	}

	@Override
	public boolean save(Accident value) {
		ConsumerEx<Session> task = (s) -> s.saveOrUpdate(value);
		return execute(task);
	}

	@Override
	public boolean deleteById(Integer id) {
		ConsumerEx<Session> task = (s) -> {
			String hql = "DELETE FROM Accident a WHERE a.id = :fId";
			s.createQuery(hql).setParameter("fId", id).executeUpdate();
		};
		return execute(task);
	}
}
