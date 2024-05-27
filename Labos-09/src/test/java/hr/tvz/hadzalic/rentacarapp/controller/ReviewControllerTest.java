package hr.tvz.hadzalic.rentacarapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hr.tvz.hadzalic.rentacarapp.entity.Review;
import hr.tvz.hadzalic.rentacarapp.service.ReviewService;
import io.jsonwebtoken.lang.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReviewControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ReviewService reviewService;

    @Test
    void getAll() throws Exception {
        Review revOne = new Review(1L, "One", "test1", 1, null,null);
        Review revTwo = new Review(2L, "Two", "test2", 1, null,null);
        Review revThree = new Review(1L, "Three", "test3", 1, null,null);

        List<Review> reviews = List.of(revOne, revTwo, revThree);
        Mockito.when(reviewService.getAll()).thenReturn(reviews);
        mvc.perform(MockMvcRequestBuilders.get("/review")
                .with(user("admin").password("root").roles("ROLES_ADMIN"))
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("One"))
                .andExpect(jsonPath("$[0].text").value("test1"))
                .andDo(print());
    }

    @Test
    void getReviewById() throws Exception {
        Review revTwo = new Review(2L, "Two", "test2", 1, null,null);

        Mockito.when(reviewService.getByID(2L)).thenReturn(Optional.of(revTwo));
        mvc.perform(MockMvcRequestBuilders.get("/review/id/2")
                        .with(user("admin").password("root").roles("ROLES_ADMIN"))
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Two"))
                .andExpect(jsonPath("$.text").value("test2"))
                .andDo(print());

    }


    @Test
    void save() throws Exception {
        Review newReview = new Review(10L, "test10","testText", 5, null, null);

        Mockito.when(reviewService.save(newReview)).thenReturn(Optional.of(newReview));

        String requestBody = mapper.writeValueAsString(newReview);

        mvc.perform(post("/review")
                        .with(user("admin").password("root").roles("ROLES_ADMIN"))
                        .with(csrf())
                        .accept(MediaType.APPLICATION_JSON).contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andDo(print())
        ;
    }

    @Test
    void updaete() throws Exception{
        Long reviewId = 1L;
        Review updatedReview = new Review(1L, "updated", "Updated text", 5, null, null);

        Mockito.when(reviewService.update(reviewId, updatedReview)).thenReturn(Optional.of(updatedReview));

        String requestBody = mapper.writeValueAsString(updatedReview);

        mvc.perform(put("/review/1")
                        .with(user("admin").password("root").roles("ROLES_ADMIN"))
                        .with(csrf())
                        .contentType("application/json")
                        .accept(MediaType.APPLICATION_JSON).contentType("application/json")
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("updated"))
                .andDo(print());
    }

    @Test
    void delete() throws Exception{
        Long userId = 2L;
        Review revOne = new Review(1L, "One", "test1", 1, null,null);
        Review revTwo = new Review(2L, "Two", "test2", 1, null,null);
        Review revThree = new Review(1L, "Three", "test3", 1, null,null);

        List<Review> reviews = List.of(revOne, revTwo, revThree);

        Mockito.doNothing().when(reviewService).delete(userId);

        mvc.perform(MockMvcRequestBuilders.delete("/review/2")
                        .with(user("admin").password("root").roles("ROLES_ADMIN"))
                        .with(csrf()))

                .andExpect(status().isNoContent())
                .andDo(print());
    }
}