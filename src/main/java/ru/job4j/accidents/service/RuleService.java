package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;

import java.util.List;
import java.util.Set;

public interface RuleService {

    public List<Rule> getAllRules();

    public Set<Rule> findByIds(String[] ids, Accident accident);
}
