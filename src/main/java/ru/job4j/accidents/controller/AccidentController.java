package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.service.AbstractAccidentService;
import ru.job4j.accidents.service.AccidentTypeService;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class AccidentController {

    private final AbstractAccidentService accidentService;
    private final AccidentTypeService accidentTypeService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.getAllTypes());
        return "accident/create";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident) {
        int id = accident.getType().getId();
        accident.setType(accidentTypeService.findById(id));
        accidentService.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("types", accidentTypeService.getAllTypes());
        model.addAttribute("accident", accidentService.findById(id).get());
        return "accident/update";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident) {
        int id = accident.getType().getId();
        accident.setType(accidentTypeService.findById(id));
        accidentService.create(accident);
        return "redirect:/index";
    }
}
