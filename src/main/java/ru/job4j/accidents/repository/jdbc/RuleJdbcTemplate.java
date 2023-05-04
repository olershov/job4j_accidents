package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.repository.RuleStore;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Repository
public class RuleJdbcTemplate implements RuleStore {

    private final JdbcTemplate jdbc;
    private final RowMapper<Rule> rowMapper = (rs, row) -> {
        Rule rule = new Rule();
        rule.setId(rs.getInt("id"));
        rule.setName(rs.getString("name"));
        return rule;
    };

    @Override
    public List<Rule> getAllRules() {
        return jdbc.query("select * from rules", rowMapper);
    }

    @Override
    public Set<Rule> findByIds(String ids) {
        String query = "select * from rules where id in (".concat(ids).concat(")");
        List<Rule> result = jdbc.query(query, rowMapper);
        return new HashSet<>(result);
    }

    public Set<Rule> findRulesByAccidentId(int id) {
       List<Rule> rules = jdbc.query(
               "select * from rules where id in (select rule_id from accidents_rules where accident_id = ?)",
                rowMapper, id
       );
        return new HashSet<>(rules);
    }
}
