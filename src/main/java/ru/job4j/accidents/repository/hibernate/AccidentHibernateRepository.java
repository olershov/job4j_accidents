package ru.job4j.accidents.repository.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentStore;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class AccidentHibernateRepository implements AccidentStore {

    private final CrudRepository crudRepository;

    @Override
    public List<Accident> findAll() {
        return crudRepository.query(
                "select distinct ac from Accident ac left join fetch ac.type left join fetch ac.rules",
                Accident.class);
    }

    @Override
    public Accident create(Accident accident) {
        crudRepository.run(session -> session.save(accident));
        return accident;
    }

    @Override
    public boolean update(Accident accident) {
        Accident beforeUpdate = new Accident(accident.getId(), accident.getName(), accident.getText(),
                accident.getAddress(), accident.getType(), accident.getRules());
        crudRepository.run(session -> session.merge(accident));
        return !accident.equals(beforeUpdate);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return crudRepository.optional(
                "from Accident ac left join fetch ac.type left join fetch ac.rules where ac.id = :fId",
        Accident.class, Map.of("fId", id));
    }
}
