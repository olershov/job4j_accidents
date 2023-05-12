package ru.job4j.accidents.service.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.hibernate.AccidentHibernateRepository;
import ru.job4j.accidents.service.AccidentService;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class AccidentHibernateService implements AccidentService {

    private final AccidentHibernateRepository repository;

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
