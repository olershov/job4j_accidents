package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Rule;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RuleService {

    private List<Rule> rules = new ArrayList<>();

    public RuleService() {
        rules.add(new Rule(1, "Статья 1"));
        rules.add(new Rule(2, "Статья 2"));
        rules.add(new Rule(3, "Статья 3"));
    }

    public List<Rule> getAllRules() {
        return List.copyOf(rules);
    }

    public Set<Rule> findByIds(String[] ids) {
        Set<Rule> result = new HashSet<>();
        for (String id : ids) {
            result.add(rules.get(Integer.parseInt(id) - 1));
        }
       return result;
    }
}
