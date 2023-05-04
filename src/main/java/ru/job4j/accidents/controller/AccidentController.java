package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.jdbc.AccidentJdbcService;
import ru.job4j.accidents.service.jdbc.AccidentTypeJdbcService;
import ru.job4j.accidents.service.jdbc.RuleJdbcService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class AccidentController {

    private final AccidentJdbcService accidentService;
    private final AccidentTypeJdbcService accidentTypeService;
    private final RuleJdbcService ruleService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", accidentTypeService.getAllTypes());
        model.addAttribute("rules", ruleService.getAllRules());
        return "accident/create";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        accidentTypeService.findById(accident);
        ruleService.findByIds(req.getParameterValues("rIds"), accident);
        accidentService.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdateAccident")
    public String update(@RequestParam("id") int id, Model model) {
        Optional<Accident> result = accidentService.findById(id);
        if (result.isEmpty()) {
            return "error/404";
        }
        model.addAttribute("types", accidentTypeService.getAllTypes());
        model.addAttribute("rules", ruleService.getAllRules());
        model.addAttribute("accident", result.get());
        return "accident/update";
    }

    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, HttpServletRequest req) {
        accidentTypeService.findById(accident);
        ruleService.findByIds(req.getParameterValues("rIds"), accident);
        accidentService.update(accident);
        return "redirect:/index";
    }
}
