package ru.job4j.accidents.service.springdata;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.springdata.AccidentTypeDataRepository;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccidentTypeDataService implements AccidentTypeService {

    private final AccidentTypeDataRepository repository;

    @Override
    public List<AccidentType> getAllTypes() {
        return repository.findAll();
    }

    @Override
    public Optional<AccidentType> findById(Accident accident) {
        Optional<AccidentType> result =  repository.findById(accident.getType().getId());
        accident.setType(result.get());
        return result;
    }
}
