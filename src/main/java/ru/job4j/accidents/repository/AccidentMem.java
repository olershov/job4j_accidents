package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem implements AccidentRepository {

    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();
    private static AtomicInteger id = new AtomicInteger(1);
    private final List<AccidentType> types = new AccidentTypeService().getAllTypes();

    public AccidentMem() {
        Accident accident1 = new Accident(id.incrementAndGet(), "Превышение скорости", "Превышение скорости на 20 км/ч",
                "ул. Волгоградский проспект", types.get(3));
        Accident accident2 = new Accident(id.incrementAndGet(), "Разметка", "Движение по выделенной полосе для общественного транспорта",
                "ул. Земляной Вал", types.get(3));
        Accident accident3 = new Accident(id.incrementAndGet(), "Ограничение дорожных знаков", "Остановка в запрещенном месте",
                "Луговой проезд", types.get(3));
       accidents.put(accident1.getId(), accident1);
       accidents.put(accident2.getId(), accident2);
       accidents.put(accident3.getId(), accident3);
    }

    @Override
    public List<Accident> findAll() {
        List<Accident> result = new ArrayList<>(accidents.values());
        return result;
    }

    @Override
    public Accident create(Accident accident) {
        if (accident.getId() == 0) {
            accident.setId(id.incrementAndGet());
        }
        accidents.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.of(accidents.get(id));
    }
}
