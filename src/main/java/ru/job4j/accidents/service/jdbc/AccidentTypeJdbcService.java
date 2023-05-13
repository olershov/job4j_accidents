package ru.job4j.accidents.service.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.jdbc.AccidentTypeJdbcTemplate;
import ru.job4j.accidents.service.AccidentTypeService;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccidentTypeJdbcService implements AccidentTypeService {

    private final AccidentTypeJdbcTemplate accidentTypeRepository;

    @Override
    public List<AccidentType> getAllTypes() {
        return accidentTypeRepository.getAllTypes();
    }

    @Override
    public Optional<AccidentType> findById(Accident accident) {
        int id = accident.getType().getId();
        Optional<AccidentType> result = accidentTypeRepository.findById(id);
        accident.setType(result.get());
        return result;
    }
}
