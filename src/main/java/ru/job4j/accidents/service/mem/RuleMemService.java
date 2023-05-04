package ru.job4j.accidents.service.mem;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.RuleService;

import java.util.*;

@Service
public class RuleMemService implements RuleService {

    Map<Integer, Rule> rules = new HashMap<>();

    public RuleMemService() {
        rules.put(1, new Rule(1, "Статья 1"));
        rules.put(2, new Rule(2, "Статья 2"));
        rules.put(3, new Rule(3, "Статья 3"));
    }

    @Override
    public List<Rule> getAllRules() {
        List<Rule> result = new ArrayList<>(rules.values());
        return result;
    }

    @Override
    public Set<Rule> findByIds(String[] ids, Accident accident) {
        Set<Rule> result = new HashSet<>();
        for (String id : ids) {
            result.add(rules.get(Integer.parseInt(id)));
        }
        accident.setRules(result);
        return result;
    }
}
