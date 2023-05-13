package ru.job4j.accidents.service.springdata;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.springdata.AccidentDataRepository;
import ru.job4j.accidents.service.AccidentService;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccidentDataService implements AccidentService {

    private final AccidentDataRepository accidentRepository;

    @Override
    public List<Accident> findAll() {
        return accidentRepository.findAll(Sort.by("id"));
    }

    @Override
    public Accident create(Accident accident) {
        return accidentRepository.save(accident);
    }

    @Override
    public boolean update(Accident accident) {
        if (accidentRepository.findById(accident.getId()).isEmpty()) {
            return false;
        }
        accidentRepository.save(accident);
        return true;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }
}
