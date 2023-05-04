package ru.job4j.accidents.service.mem;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.mem.AccidentMemRepository;
import ru.job4j.accidents.service.AccidentService;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccidentMemService implements AccidentService {

    private final AccidentMemRepository repository;

    @Override
    public List<Accident> findAll() {
        return repository.findAll();
    }

    @Override
    public Accident create(Accident accident) {
        return repository.create(accident);
    }

    @Override
    public boolean update(Accident accident) {
        return repository.update(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return repository.findById(id);
    }
}
