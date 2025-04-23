package pl.edu.pwr.lab06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.lab06.entity.Subkonto;

import java.util.Optional;

public interface SubkontoRepository extends JpaRepository<Subkonto, Long> {
    boolean existsByLogin(String login);
    Optional<Subkonto> findByLogin(String login);
}
