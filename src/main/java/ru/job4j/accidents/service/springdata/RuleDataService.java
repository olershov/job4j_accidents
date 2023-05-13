package ru.job4j.accidents.service.springdata;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.springdata.RuleDataRepository;
import ru.job4j.accidents.service.RuleService;

import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RuleDataService implements RuleService {

    private final RuleDataRepository repository;

    @Override
    public List<Rule> getAllRules() {
        return repository.findAll();
    }

    @Override
    public Set<Rule> findByIds(String[] ids, Accident accident) {
        Set<Rule> result = new HashSet<>();
        List<Integer> idsList = Arrays.stream(ids).map(Integer::valueOf).collect(Collectors.toList());
        Iterable<Rule> rules = repository.findAllById(idsList);
        rules.forEach(result::add);
        accident.setRules(result);
        return result;
    }
}
