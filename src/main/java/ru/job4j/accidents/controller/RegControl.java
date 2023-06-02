package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.springdata.AuthorityDataService;
import ru.job4j.accidents.service.springdata.UserDataService;

import java.util.Optional;

@AllArgsConstructor
@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserDataService users;
    private final AuthorityDataService authorities;

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER").get());
        Optional<User> result = users.save(user);
        if (result.isPresent()) {
            return "redirect:/login";
        }
        model.addAttribute("errorMessage", "This login already in use !!!");
        return "auth/reg";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "auth/reg";
    }
}