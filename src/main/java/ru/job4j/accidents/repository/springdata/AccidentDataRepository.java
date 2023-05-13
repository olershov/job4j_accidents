package ru.job4j.accidents.repository.springdata;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.List;

public interface AccidentDataRepository extends CrudRepository<Accident, Integer> {

    List<Accident> findAll(Sort sort);
}
