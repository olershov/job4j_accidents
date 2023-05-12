package ru.job4j.accidents.repository.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeStore;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class AccidentTypeHibernateRepository implements AccidentTypeStore {

    private final CrudRepository crudRepository;

    @Override
    public List<AccidentType> getAllTypes() {
        return crudRepository.query("from AccidentType", AccidentType.class);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return crudRepository.optional("from AccidentType where id = :fId", AccidentType.class, Map.of("fId", id));
    }
}
