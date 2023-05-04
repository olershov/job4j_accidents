package ru.job4j.accidents.repository;
import ru.job4j.accidents.model.AccidentType;
import java.util.List;
import java.util.Optional;

public interface AccidentTypeStore {

    public List<AccidentType> getAllTypes();

    public Optional<AccidentType> findById(int id);
}
