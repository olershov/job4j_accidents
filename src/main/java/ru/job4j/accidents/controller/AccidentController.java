package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AbstractAccidentService;

@Controller
@AllArgsConstructor
public class AccidentController {

    private final AbstractAccidentService accidentService;

    @GetMapping("/createAccident")
    public String viewCreateAccident() {
        return "accident/create";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        accidentService.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id).get());
        return "accident/update";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident) {
        accidentService.create(accident);
        return "redirect:/index";
    }
}
