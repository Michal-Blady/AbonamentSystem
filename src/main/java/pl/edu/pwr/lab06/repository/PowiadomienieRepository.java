package pl.edu.pwr.lab06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.lab06.entity.Powiadomienie;
import java.util.List;

@Repository
public interface PowiadomienieRepository extends JpaRepository<Powiadomienie, Long> {
    List<Powiadomienie> findByKlientId(Long klientId);
    List<Powiadomienie> findAll();
}
