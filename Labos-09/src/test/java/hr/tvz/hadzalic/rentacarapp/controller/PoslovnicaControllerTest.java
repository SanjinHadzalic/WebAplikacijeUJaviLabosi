package hr.tvz.hadzalic.rentacarapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.hadzalic.rentacarapp.dto.PoslovnicaDTO;
import hr.tvz.hadzalic.rentacarapp.entity.Poslovnica;
import hr.tvz.hadzalic.rentacarapp.entity.Review;
import hr.tvz.hadzalic.rentacarapp.service.PoslovnicaService;
import hr.tvz.hadzalic.rentacarapp.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PoslovnicaControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private PoslovnicaService service;

    @Test
    void getAll() throws Exception{
        PoslovnicaDTO one = new PoslovnicaDTO( "One", "test1", 1, null);
        PoslovnicaDTO two = new PoslovnicaDTO( "Two", "test2", 1, null);

        List<PoslovnicaDTO> poslovnice = List.of(one, two);
        Mockito.when(service.findAll()).thenReturn(poslovnice);
        mvc.perform(MockMvcRequestBuilders.get("/poslovnica")
                        .with(user("admin").password("root").roles("ROLES_ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("One"))
                .andExpect(jsonPath("$[0].adress").value("test1"))
                .andDo(print());

    }

    @Test
    void save() throws Exception{
        PoslovnicaDTO one = new PoslovnicaDTO( "One", "test1", 1, null);

        Mockito.when(service.save(one)).thenReturn(Optional.of(one));

    }

    @Test
    void delete() throws Exception{
        Long poslovnicaId = 2L;

        Mockito.doNothing().when(service).delete(poslovnicaId);
        mvc.perform(MockMvcRequestBuilders.get("/poslovnica/2")
                        .with(user("admin").password("root").roles("ROLES_ADMIN"))
                        .with(csrf())).andExpect(status().isNoContent())
                .andDo(print());

    }
}