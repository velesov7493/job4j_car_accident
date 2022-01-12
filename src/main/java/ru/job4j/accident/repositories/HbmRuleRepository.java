package ru.job4j.accident.repositories;

import org.hibernate.Session;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.models.Rule;
import ru.job4j.accident.repositories.rules.ConsumerEx;
import ru.job4j.accident.repositories.rules.FunctionEx;

import java.util.Collection;
import java.util.List;

@Repository
public class HbmRuleRepository extends GenericHbmStore<Integer, Rule> {

	public HbmRuleRepository(LocalSessionFactoryBean sfb) {
		super(sfb);
	}

	@Override
	public Collection<Rule> findAll() {
		FunctionEx<Session, List<Rule>> f = (s) ->
			s.createQuery("FROM Rule", Rule.class)
			.getResultList();
		return select(f);
	}

	@Override
	public Rule findById(Integer id) {
		FunctionEx<Session, Rule> f = (s) ->
			(Rule) s.createQuery("FROM Rule r WHERE r.id = :fId")
			.setParameter("fId", id)
			.getSingleResult();
		return select(f);
	}

	@Override
	public boolean save(Rule value) {
		ConsumerEx<Session> task = (s) -> s.saveOrUpdate(value);
		return execute(task);
	}

	@Override
	public boolean deleteById(Integer id) {
		ConsumerEx<Session> task = (s) -> {
			String hql = "DELETE FROM Rule r WHERE r.id = :fId";
			s.createQuery(hql).setParameter("fId", id).executeUpdate();
		};
		return execute(task);
	}
}
