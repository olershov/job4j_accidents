package ru.job4j.accidents.service.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.RuleService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import ru.job4j.accidents.repository.hibernate.RuleHibernateRepository;

@AllArgsConstructor
@Service
public class RuleHibernateService implements RuleService {

    private final RuleHibernateRepository repository;

    @Override
    public List<Rule> getAllRules() {
        return repository.getAllRules();
    }

    public Set<Rule> findByIds(String[] ids, Accident accident) {
        List<Integer> idsList = new ArrayList<>();
        Arrays.stream(ids).forEach(id -> idsList.add(Integer.valueOf(id)));
        Set<Rule> result = repository.findByIds(idsList);
        accident.setRules(result);
        return result;
    }
}
