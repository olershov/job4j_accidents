package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

public interface AccidentRepository {

    public List<Accident> findAll();

    public Accident create(Accident accident);

    public Optional<Accident> findById(int id);
}
