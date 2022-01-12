package ru.job4j.accident.repositories;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.AccidentType;
import ru.job4j.accident.repositories.rules.ConsumerEx;
import ru.job4j.accident.repositories.rules.FunctionEx;

import java.util.Collection;
import java.util.List;

@Repository
public class HbmAccidentTypeRepository extends GenericHbmStore<Integer, AccidentType> {

	public HbmAccidentTypeRepository(LocalSessionFactoryBean sfb) {
		super(sfb);
	}

	@Override
	public Collection<AccidentType> findAll() {
		FunctionEx<Session, List<AccidentType>> f = (s) ->
			s.createQuery("FROM AccidentType", AccidentType.class)
			.getResultList();
		return select(f);
	}

	@Override
	public AccidentType findById(Integer id) {
		FunctionEx<Session, AccidentType> f = (s) -> {
			String hql = "FROM AccidentType t WHERE t.id = :fId";
			return
					(AccidentType) s.createQuery(hql, AccidentType.class)
					.setParameter("fId", id).getSingleResult();
		};
		return select(f);

	}

	@Override
	public boolean save(AccidentType value) {
		ConsumerEx<Session> task = (s) -> s.saveOrUpdate(value);
		return execute(task);
	}

	@Override
	public boolean deleteById(Integer id) {
		ConsumerEx<Session> task = (s) -> {
			String hql = "DELETE FROM AccidentType t WHERE t.id = :fId";
			s.createQuery(hql).setParameter("fId", id).executeUpdate();
		};
		return execute(task);
	}
}