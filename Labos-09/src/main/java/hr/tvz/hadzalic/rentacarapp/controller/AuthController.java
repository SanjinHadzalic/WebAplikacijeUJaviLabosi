package hr.tvz.hadzalic.rentacarapp.controller;

import hr.tvz.hadzalic.rentacarapp.dto.AuthRequestDTO;
import hr.tvz.hadzalic.rentacarapp.dto.JwtResponseDTO;
import hr.tvz.hadzalic.rentacarapp.dto.RefreshTokenRequestDTO;
import hr.tvz.hadzalic.rentacarapp.entity.RefreshToken;
import hr.tvz.hadzalic.rentacarapp.security.UserInfo;
import hr.tvz.hadzalic.rentacarapp.service.JwtService;
import hr.tvz.hadzalic.rentacarapp.service.RefreshTokenService;
import hr.tvz.hadzalic.rentacarapp.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private RefreshTokenService refreshTokenService;
    private UserDetailsServiceImpl userDetailsService;


    @PostMapping("/api/v1/login")
    public JwtResponseDTO authenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
        if(authentication.isAuthenticated()) {
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());
            return JwtResponseDTO.builder()
                    .accessToken(jwtService.generateToken(authRequestDTO.getUsername()))
                    .token(refreshToken.getToken())
                    .build();
        } else {
            throw new UsernameNotFoundException("invalid user request!!!");
        }
    }

    @PostMapping("/api/v1/refreshToken")
    public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO){
        return refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUserInfo)
                .map(userInfo -> {
                    String accessToken = jwtService.generateToken(userInfo.getUsername());
                    return JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequestDTO.getToken()).build();
                }).orElseThrow(() ->new RuntimeException("Refresh Token is not in DB..!!"));
    }

    @PostMapping("/api/v1/logout")
    public void logout() {
        System.out.println("Logout...");
    }

    @PostMapping("/api/v1/register")
    public ResponseEntity<UserInfo> registerUser(@RequestBody UserInfo userInfo) {
        log.info("Provjera: " + userInfo);
        userDetailsService.save(userInfo);
        return  ResponseEntity.ofNullable(userInfo);
    }

    @GetMapping("/api/v1/users")
    public List<UserInfo> getAll() {
        return userDetailsService.getAll();
    }
}
