package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;
import java.util.List;
import java.util.Set;

public interface RuleStore {

    public List<Rule> getAllRules();

    public Set<Rule> findByIds(String ids);

}
