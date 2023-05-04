package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import ru.job4j.accidents.model.Accident;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.repository.AccidentStore;
import java.sql.PreparedStatement;
import java.util.*;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentStore {

    private final JdbcTemplate jdbc;
    private static final String FIND_ALL = "select * from accidents ac "
            + "left join accident_types at on ac.type_id = at.id "
            + "left join accidents_rules ar on ac.id = ar.accident_id "
            + "left join rules r on ar.rule_id = r.id";
    private static final String INSERT = "insert into accidents (name, text, address, type_id) values (?, ?, ?, ?)";
    private static final String UPDATE = "update accidents set name = ?, text = ?, address = ?, type_id = ? where id = ?";
    private static final String DELETE_ACCIDENTS_RULES = "delete from accidents_rules where accident_id = ?";
    private static final String INSERT_ACCIDENTS_RULES = "insert into accidents_rules (accident_id, rule_id) values (?, ?)";

    @Override
    public List<Accident> findAll() {
        Map<Integer, Accident> map = new HashMap<>();
        jdbc.query(FIND_ALL, new AccidentRowMapper(map));
        return new ArrayList<>(map.values());
    }

    @Override
    public Accident create(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(
                    INSERT, new String[] {"id"}
            );
            ps.setString(1, accident.getName());
            ps.setString(2, accident.getText());
            ps.setString(3, accident.getAddress());
            ps.setInt(4, accident.getType().getId());
            return ps;
        }, keyHolder);
        accident.setId((Integer) keyHolder.getKey());
        createRelationAccidentsRules(accident);
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        int id = accident.getId();
        jdbc.update(DELETE_ACCIDENTS_RULES, id);
        jdbc.update(
                UPDATE, accident.getName(), accident.getText(), accident.getAddress(), accident.getType().getId(), id
        );
        createRelationAccidentsRules(accident);
        return true;
    }

    @Override
    public Optional<Accident> findById(int id) {
        Map<Integer, Accident> map = new HashMap<>();
        jdbc.query(FIND_ALL + " where ac.id = ?", new AccidentRowMapper(map), id);
        return Optional.ofNullable(map.get(id));
    }

    private void createRelationAccidentsRules(Accident accident) {
        accident.getRules().forEach(rule ->
                jdbc.update(INSERT_ACCIDENTS_RULES,
                        accident.getId(), rule.getId())
        );
    }
}
