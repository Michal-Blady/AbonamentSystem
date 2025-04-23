package pl.edu.pwr.lab06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pwr.lab06.entity.Naleznosci;

@Repository
public interface NaleznosciRepository extends JpaRepository<Naleznosci, Long> {
}
