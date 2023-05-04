package ru.job4j.accidents.repository.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;

@AllArgsConstructor
public class AccidentRowMapper implements RowMapper<Accident> {

    private final Map<Integer, Accident> map;

    @Override
    public Accident mapRow(ResultSet rs, int rowNum) throws SQLException {
        Accident result = map.get(rs.getInt("id"));
        if (result == null) {
            result = new Accident();
            result.setRules(new HashSet<>());
            result.setId(rs.getInt("id"));
            result.setName(rs.getString("name"));
            result.setText(rs.getString("text"));
            result.setAddress(rs.getString("address"));
            AccidentType accidentType = new AccidentType(rs.getInt(6), rs.getString(7));
            result.setType(accidentType);
            }
            int ruleId = rs.getInt(11);
            if (ruleId != 0) {
                result.addRule(new Rule(ruleId, rs.getString(12)));
            }
        map.put(result.getId(), result);
        return result;
    }
}
