package ru.job4j.accidents.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
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
import ru.job4j.accidents.service.springdata.AccidentTypeDataService;
import ru.job4j.accidents.service.springdata.RuleDataService;
import java.util.Optional;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class AccidentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AccidentDataService accidentService;

    @MockBean
    AccidentTypeDataService accidentTypeService;

    @MockBean
    RuleDataService ruleService;

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

    @Test
    @WithMockUser
    public void whenSaveAccident() throws Exception {
        Rule rule = new Rule(1, "Статья");
        AccidentType type = new AccidentType(1, "Машины");
        Accident accident = new Accident(0, "Имя", "Описание", "Адрес", type, Set.of(rule));
        when(accidentTypeService.findById(accident)).thenReturn(Optional.of(type));
        when(ruleService.findByIds(new String[]{"1"}, accident)).thenReturn(Set.of(rule));
        this.mockMvc.perform(post("/saveAccident")
                        .param("id", "0")
                        .param("name", "Имя")
                        .param("text", "Описание")
                        .param("address", "Адрес")
                        .param("type.id", "1")
                        .param("rIds", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));

        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).create(argument.capture());
        assertEquals(argument.getValue().getId(), 0);
        assertEquals(argument.getValue().getName(), "Имя");
        assertEquals(argument.getValue().getText(), "Описание");
        assertEquals(argument.getValue().getAddress(), "Адрес");
        assertEquals(argument.getValue().getType(), type);
    }

    @Test
    @WithMockUser
    public void whenUpdateAccident() throws Exception {
        Rule rule = new Rule(1, "Статья");
        AccidentType type = new AccidentType(1, "Машины");
        Accident accident = new Accident(0, "Имя", "Описание", "Адрес", type, Set.of(rule));
        when(accidentTypeService.findById(accident)).thenReturn(Optional.of(type));
        when(ruleService.findByIds(new String[]{"1"}, accident)).thenReturn(Set.of(rule));
        this.mockMvc.perform(post("/updateAccident")
                        .param("id", "0")
                        .param("name", "Имя")
                        .param("text", "Описание")
                        .param("address", "Адрес")
                        .param("type.id", "1")
                        .param("rIds", "1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));

        ArgumentCaptor<Accident> argument = ArgumentCaptor.forClass(Accident.class);
        verify(accidentService).update(argument.capture());
        assertEquals(argument.getValue().getId(), 0);
        assertEquals(argument.getValue().getName(), "Имя");
        assertEquals(argument.getValue().getText(), "Описание");
        assertEquals(argument.getValue().getAddress(), "Адрес");
        assertEquals(argument.getValue().getType(), type);
    }

    @Test
    @WithMockUser
    public void whenSaveAccidentAndAccidentTypeNotFoundShouldReturnErrorPage() throws Exception {
        Accident accident = new Accident(
                0, "Имя", "Описание", "Адрес", new AccidentType(1, "Type"), Set.of(new Rule(1, "Rule"))
        );
        when(accidentTypeService.findById(accident)).thenReturn(Optional.empty());
        this.mockMvc.perform(post("/saveAccident")
                        .param("id", "0")
                        .param("name", "Имя")
                        .param("text", "Описание")
                        .param("address", "Адрес")
                        .param("type.id", "1")
                        .param("rIds", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("error/404"));
    }

    @Test
    @WithMockUser
    public void whenUpdateAccidentAndAccidentTypeNotFoundShouldReturnErrorPage() throws Exception {
        Accident accident = new Accident(
                0, "Имя", "Описание", "Адрес", new AccidentType(1, "Type"), Set.of(new Rule(1, "Rule"))
        );
        when(accidentTypeService.findById(accident)).thenReturn(Optional.empty());
        this.mockMvc.perform(post("/updateAccident")
                        .param("id", "0")
                        .param("name", "Имя")
                        .param("text", "Описание")
                        .param("address", "Адрес")
                        .param("type.id", "1")
                        .param("rIds", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("error/404"));
    }
}