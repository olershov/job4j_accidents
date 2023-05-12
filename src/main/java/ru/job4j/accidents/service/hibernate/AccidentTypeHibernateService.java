package ru.job4j.accidents.service.hibernate;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.hibernate.AccidentTypeHibernateRepository;
import ru.job4j.accidents.service.AccidentTypeService;
import java.util.List;

@AllArgsConstructor
@Service
public class AccidentTypeHibernateService implements AccidentTypeService {

    private final AccidentTypeHibernateRepository repository;

    @Override
    public List<AccidentType> getAllTypes() {
        return repository.getAllTypes();
    }

    @Override
    public AccidentType findById(Accident accident) {
        int id = accident.getType().getId();
        AccidentType result = repository.findById(id).get();
        accident.setType(result);
        return result;
    }
}
