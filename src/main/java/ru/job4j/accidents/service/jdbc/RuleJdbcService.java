package ru.job4j.accidents.service.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.jdbc.RuleJdbcTemplate;
import ru.job4j.accidents.service.RuleService;
import java.util.*;

@AllArgsConstructor
@Service
public class RuleJdbcService implements RuleService {

    private final RuleJdbcTemplate ruleRepository;

    @Override
    public List<Rule> getAllRules() {
        return ruleRepository.getAllRules();
    }

    @Override
    public Set<Rule> findByIds(String[] ids, Accident accident) {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(ids).forEach(id -> sb.append(id).append(", "));
        Set<Rule> result = ruleRepository.findByIds(sb.substring(0, sb.length() - 2));
        accident.setRules(result);
        return result;
    }
}
