package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.accidents.Main;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Rule;
import ru.job4j.accidents.service.springdata.AccidentDataService;
import java.util.Optional;
import java.util.Set;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AccidentDataService accidentService;

    @Test
    @WithMockUser
    public void shouldReturnCreatePage() throws Exception {
        this.mockMvc.perform(get("/createAccident"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/create"));
    }

    @Test
    @WithMockUser
    public void shouldReturnUpdatePage() throws Exception {
        Rule rule = new Rule(1, "Статья 1");
        AccidentType type = new AccidentType(1, "Две Машины");
        Accident accident = new Accident(5, "Имя", "Описание", "Адрес", type, Set.of(rule));
        when(accidentService.findById(accident.getId())).thenReturn(Optional.of(accident));
        this.mockMvc.perform(get("/formUpdateAccident?id=5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("accident/update"));
    }

    @Test
    @WithMockUser
    public void shouldReturnErrorPage() throws Exception {
        when(accidentService.findById(4)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/formUpdateAccident?id=4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("error/404"));
    }
}