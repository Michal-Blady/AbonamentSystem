package pl.edu.pwr.lab06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.lab06.entity.Klient;
import java.util.List;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Long> {
    List<Klient> findByImieContainingIgnoreCaseAndNazwiskoContainingIgnoreCase(String imie, String nazwisko);
}
