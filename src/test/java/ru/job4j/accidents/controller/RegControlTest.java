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
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.springdata.AuthorityDataService;
import ru.job4j.accidents.service.springdata.UserDataService;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
class RegControlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserDataService userDataService;

    @MockBean
    AuthorityDataService authorityDataService;

    @Test
    @WithMockUser
    public void shouldReturnRegPage() throws Exception {
        this.mockMvc.perform(get("/reg"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("auth/reg"));
    }

    @Test
    @WithMockUser
    public void whenRegSuccess() throws Exception {
        when(authorityDataService.findByAuthority(anyString())).thenReturn(Optional.of(new Authority()));
        when(userDataService.save(any(User.class))).thenReturn(Optional.of(new User()));
        this.mockMvc.perform(post("/reg")
                        .param("username", "user")
                        .param("password", "123"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(userDataService).save(argument.capture());
        assertEquals(argument.getValue().getUsername(), "user");
    }

    @Test
    @WithMockUser
    public void whenUserAlreadyExists() throws Exception {
        Authority authority = new Authority(1, "Authority");
        User user = new User(1, "user", "123", true, authority);
        when(userDataService.save(user)).thenReturn(Optional.empty());
        when(authorityDataService.findByAuthority(anyString())).thenReturn(Optional.of(authority));
        this.mockMvc.perform(post("/reg")
                        .param("username", "user")
                        .param("password", "123"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("errorMessage", "This login already in use !!!"))
                .andExpect(view().name("auth/reg"));
    }
}