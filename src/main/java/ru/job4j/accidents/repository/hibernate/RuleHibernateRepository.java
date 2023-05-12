package ru.job4j.accidents.repository.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleStore;

import java.util.*;

@AllArgsConstructor
@Repository
public class RuleHibernateRepository implements RuleStore {

    private final CrudRepository crudRepository;

    @Override
    public List<Rule> getAllRules() {
        return crudRepository.query("from Rule", Rule.class);
    }

    public Set<Rule> findByIds(List<Integer> ids) {
        List<Rule> result = crudRepository.query("from Rule where id in :fId", Rule.class, Map.of("fId", ids));
        return new HashSet<>(result);
    }
}
