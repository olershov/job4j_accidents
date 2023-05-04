package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;

public interface AccidentTypeService {

    public List<AccidentType> getAllTypes();

    public AccidentType findById(Accident accident);

}
