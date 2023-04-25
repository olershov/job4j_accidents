package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;
import java.util.List;
import java.util.Optional;

public interface AbstractAccidentService {

    public List<Accident> findAll();

    public Accident create(Accident accident);

    public boolean update(Accident accident);

    public Optional<Accident> findById(int id);
}
