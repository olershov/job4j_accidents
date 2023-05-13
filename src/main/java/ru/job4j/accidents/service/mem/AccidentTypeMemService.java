package ru.job4j.accidents.service.mem;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.*;

@Service
public class AccidentTypeMemService implements AccidentTypeService {

    private Map<Integer, AccidentType> types = new HashMap();

    public AccidentTypeMemService() {
        types.put(1, new AccidentType(1, "Две машины"));
        types.put(2, new AccidentType(2, "Машина и человек"));
        types.put(3, new AccidentType(3, "Машина и велосипед"));
        types.put(4, new AccidentType(4, "Машина - нарушение"));
    }

    @Override
    public List<AccidentType> getAllTypes() {
        List<AccidentType> result = new ArrayList<>(types.values());
        return result;
    }

    @Override
    public Optional<AccidentType> findById(Accident accident) {
        int id = accident.getType().getId();
        AccidentType result = types.get(id);
        accident.setType(result);
        return Optional.of(result);
    }

}
