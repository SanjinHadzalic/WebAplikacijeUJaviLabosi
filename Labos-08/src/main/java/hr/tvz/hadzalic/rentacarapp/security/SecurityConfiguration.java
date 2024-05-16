package hr.tvz.hadzalic.rentacarapp.security;

import hr.tvz.hadzalic.rentacarapp.filter.JwtAuthFilter;
import hr.tvz.hadzalic.rentacarapp.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http.cors(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((auth) -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/auth/api/v1/login", "/auth/api/v1/refreshToken").permitAll()
                        .requestMatchers("/bugtracking/**").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "/vozilo/**").hasAnyRole("ROLES_ADMIN")
                        .requestMatchers(HttpMethod.POST, "/vozilo/**").hasAnyRole("ROLES_USER")
                        .requestMatchers(HttpMethod.PUT, "/vozilo/**").hasAnyRole("ROLES_USER")
                        .requestMatchers( "/vozilo/**").authenticated()
                        .requestMatchers("/review/**").authenticated()
                        .requestMatchers("/h2-console/**").authenticated()
                )
                .csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider())
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
}
