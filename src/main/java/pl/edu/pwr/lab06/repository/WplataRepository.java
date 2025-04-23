package pl.edu.pwr.lab06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pwr.lab06.entity.Wplata;

public interface WplataRepository extends JpaRepository<Wplata, Long> {
}
