package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Rule;
import java.util.List;

public interface RuleStore {

    public List<Rule> getAllRules();

}
