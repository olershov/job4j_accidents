package ru.job4j.accidents.service.springdata;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.controller.RegControl;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.springdata.UserDataRepository;
import ru.job4j.accidents.service.UserService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDataService implements UserService {

    private final UserDataRepository dataRepository;
    private static final Logger LOG = LoggerFactory.getLogger(UserDataService.class.getName());

    @Override
    public Optional<User> save(User user) {
        try {
            return Optional.of(dataRepository.save(user));
        } catch (Exception e) {
            LOG.error("Exception in UserDataService.save()", e);
        }
        return Optional.empty();
    }
}