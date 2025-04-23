package pl.edu.pwr.lab06.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.edu.pwr.lab06.entity.Subkonto;
import pl.edu.pwr.lab06.repository.SubkontoRepository;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {

    private final SubkontoRepository subkontoRepository;

    public CustomUserDetailsService(SubkontoRepository subkontoRepository) {
        this.subkontoRepository = subkontoRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Subkonto> optionalSubkonto = subkontoRepository.findByLogin(username);
        if (optionalSubkonto.isEmpty()) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika o loginie: " + username);
        }
        Subkonto subkonto = optionalSubkonto.get();
        String role = subkonto.getLogin().equals("admin") ? "ADMIN" : "USER";
        return User.withUsername(subkonto.getLogin())
                .password(subkonto.getHaslo())
                .roles(role)
                .build();
    }
}
