package hr.tvz.hadzalic.rentacarapp.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hr.tvz.hadzalic.rentacarapp.entity.Vozilo;
import hr.tvz.hadzalic.rentacarapp.filter.JwtAuthFilter;
import hr.tvz.hadzalic.rentacarapp.service.VoziloService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VoziloControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private VoziloService voziloService;
    @MockBean
    private JwtAuthFilter jwtAuthFilter;

    @Test
    void shouldReturnAll() throws Exception {
//        Vozilo vozilo1 = new Vozilo();
//        vozilo1.setId(1L);
//        Vozilo vozilo2 = new Vozilo();
//        vozilo2.setId(2L);
//
//        Mockito.when(voziloService.fetchAll()).thenReturn(Arrays.asList(vozilo1, vozilo2));

        mvc.perform(MockMvcRequestBuilders
                        .get("/vozilo")
                                .with(user("admin")
                                .password("root")
                                .roles("ROLES_ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1L"));
    }

    @Test
    void getVoziloById() throws Exception {
        Vozilo vozilo1 = new Vozilo();
        vozilo1.setId(1L);

        Mockito.when(voziloService.findVoziloByID(1L)).thenReturn(vozilo1);

        mvc.perform(MockMvcRequestBuilders
                        .get("/vozilo")
                        .with(user("admin")
                                .password("root")
                                .roles("ROLES_ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1L"));

    }



    @Test
    void save() throws Exception {
        Vozilo vozilo1 = new Vozilo();
        vozilo1.setId(1L);


        mvc.perform(post("/vozilo").with(user("admin").password("root").roles("ROLES_ADMIN"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(String.valueOf(vozilo1))
                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L));
    }

}