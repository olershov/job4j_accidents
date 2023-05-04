package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeStore;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class AccidentTypeJdbcTemplate implements AccidentTypeStore {

    private final JdbcTemplate jdbc;
    private final RowMapper<AccidentType> rowMapper = (rs, row) -> {
        AccidentType type = new AccidentType();
        type.setId(rs.getInt("id"));
        type.setName(rs.getString("name"));
        return type;
    };

    @Override
    public List<AccidentType> getAllTypes() {
        return jdbc.query("select * from accident_types", rowMapper);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(jdbc.queryForObject("select * from accident_types where id = ?", rowMapper, id));
    }
}