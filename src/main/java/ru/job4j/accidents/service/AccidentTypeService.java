package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccidentTypeService {

    private List<AccidentType> types = new ArrayList<>();

    public AccidentTypeService() {
        types.add(new AccidentType(1, "Две машины"));
        types.add(new AccidentType(2, "Машина и человек"));
        types.add(new AccidentType(3, "Машина и велосипед"));
        types.add(new AccidentType(4, "Машина - нарушение"));
    }

    public List<AccidentType> getAllTypes() {
        return List.copyOf(types);
    }

    public AccidentType findById(int id) {
        return types.get(id - 1);
    }
}
