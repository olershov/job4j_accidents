package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class AccidentMem implements AccidentRepository {

    private Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public AccidentMem() {
        Accident accident1 = new Accident(1, "Превышение скорости", "Превышение скорости на 20 км/ч",
                "ул. Волгоградский проспект");
        Accident accident2 = new Accident(2, "Разметка", "Движение по выделенной полосе для общественного транспорта",
                "ул. Земляной Вал");
        Accident accident3 = new Accident(3, "Ограничение дорожных знаков", "Остановка в запрещенном месте",
                "Луговой проезд");
       accidents.put(1, accident1);
       accidents.put(2, accident2);
       accidents.put(3, accident3);
    }

    @Override
    public List<Accident> findAll() {
        List<Accident> result = new ArrayList<>(accidents.values());
        return result;
    }
}
