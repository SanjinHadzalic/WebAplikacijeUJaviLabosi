package hr.tvz.hadzalic.rentacarapp.controller;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class IssueControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String accessToken;
    private String refreshToken;
    @Test
    void getIssues() throws Exception{
        MvcResult mvcResult = mockMvc.perform(post("/auth/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"user\", \"password\":\"root\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").isNotEmpty())
//                .andExpect(jsonPath("$.refreshToken").isNotEmpty())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String contentSpring = response.getContentAsString();
        DocumentContext documentContext = JsonPath.parse(contentSpring);
        accessToken = documentContext.read("$.accessToken");
//        refreshToken = documentContext.read("$.refreshToken");
    }

    @Test
    void getIssueById() throws Exception{
        getIssues();

        // Pretpostavljamo da postoji Bug s ID-jem 1 u listi bugList
        Long issueId = 1L;

        // Očekivani JSON odgovor (ovdje je pojednostavljen)
        String expectedJsonResponse = "{\"id\":1,\"issueName\":\"First\",\"issueMessage\":\"Bug\"}";

        mockMvc.perform(get("/bugtracking/" + issueId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJsonResponse));
    }
}