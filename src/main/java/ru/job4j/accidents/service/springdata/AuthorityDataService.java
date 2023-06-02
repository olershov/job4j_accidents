package ru.job4j.accidents.service.springdata;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.repository.springdata.AuthorityDataRepository;
import ru.job4j.accidents.service.AuthorityService;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorityDataService implements AuthorityService {

    private final AuthorityDataRepository dataRepository;

    @Override
    public Optional<Authority> findByAuthority(String authority) {
        return Optional.ofNullable(dataRepository.findByAuthority(authority));
    }
}