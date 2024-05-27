package hr.tvz.hadzalic.rentacarapp.controller;

import hr.tvz.hadzalic.rentacarapp.entity.RefreshToken;
import hr.tvz.hadzalic.rentacarapp.security.UserInfo;
import hr.tvz.hadzalic.rentacarapp.security.UserRole;
import hr.tvz.hadzalic.rentacarapp.service.JwtService;
import hr.tvz.hadzalic.rentacarapp.service.RefreshTokenService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.print.attribute.standard.Media;

import java.time.Instant;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;
    @Mock
    private RefreshTokenService refreshTokenService;
    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }
    @Test
    void authenticateAndGetToken_success() throws Exception {
        Authentication authentication = mock(Authentication.class);
        UserRole userRole = new UserRole(1L, "ROLES_USER");
        List<UserRole> userRoleList = new ArrayList<>();
        userRoleList.add(userRole);
        UserInfo userInfo = new UserInfo(1L, "user", "root", userRoleList, new ArrayList<>());
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtService.generateToken(anyString())).thenReturn("accessToken");
        when(refreshTokenService.createRefreshToken(anyString())).thenReturn(
                new RefreshToken(1, "refreshToken", Instant.now().plus(Period.ofDays(1)), userInfo));

        mockMvc.perform(post("/auth/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"user\", \"password\":\"root\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("accessToken"));
//                .andExpect(jsonPath("$.refreshToken").value("refreshToken"));
    }

    @Test
    void refreshToken() {
    }

    @Test
    void logout_success() throws Exception {
        mockMvc.perform(post("/auth/api/v1/logout"))
                .andExpect(status().isOk());
    }

    @Test
    void registerUser() {
    }

    @Test
    void getAll() {
    }
}