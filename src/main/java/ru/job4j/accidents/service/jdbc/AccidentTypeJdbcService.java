package ru.job4j.accidents.service.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.jdbc.AccidentTypeJdbcTemplate;
import ru.job4j.accidents.service.AccidentTypeService;
import java.util.List;

@AllArgsConstructor
@Service
public class AccidentTypeJdbcService implements AccidentTypeService {

    private final AccidentTypeJdbcTemplate accidentTypeRepository;

    @Override
    public List<AccidentType> getAllTypes() {
        return accidentTypeRepository.getAllTypes();
    }

    @Override
    public AccidentType findById(Accident accident) {
        int id = accident.getType().getId();
        AccidentType result = accidentTypeRepository.findById(id).get();
        accident.setType(result);
        return result;
    }
}
