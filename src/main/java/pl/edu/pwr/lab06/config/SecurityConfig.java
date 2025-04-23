package pl.edu.pwr.lab06.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import pl.edu.pwr.lab06.repository.SubkontoRepository;
import pl.edu.pwr.lab06.service.CustomUserDetailsService;
import pl.edu.pwr.lab06.service.SimulatedTimeService;

import java.time.LocalDate;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
private SimulatedTimeService simulatedTimeService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/", "/login", "/css/**", "/js/**", "/admin/*.html", "/client/*.html")
                            .permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/client/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .successHandler((request, response, authentication) -> {
                            // Sprawdź, czy użytkownik ma rolę ADMIN
                            if (authentication.getAuthorities().stream()
                                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                                response.sendRedirect("/admin/dashboard");
                            } else {
                                // w innym przypadku przekieruj jako USER
                                response.sendRedirect("/client/dashboard");
                            }
                        })
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(SubkontoRepository subkontoRepository) {
        return new CustomUserDetailsService(subkontoRepository);
    }
}


