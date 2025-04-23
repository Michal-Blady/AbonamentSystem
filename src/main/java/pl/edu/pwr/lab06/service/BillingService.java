package pl.edu.pwr.lab06.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.edu.pwr.lab06.entity.Abonament;
import pl.edu.pwr.lab06.entity.Naleznosci;
import pl.edu.pwr.lab06.repository.AbonamentRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class BillingService {

    private final AbonamentRepository abonamentRepository;
    private final SimulatedTimeService simulatedTimeService;

    public BillingService(AbonamentRepository abonamentRepository, SimulatedTimeService simulatedTimeService) {
        this.abonamentRepository = abonamentRepository;
        this.simulatedTimeService = simulatedTimeService;
    }

    @Scheduled(fixedRate = 1000)
    public void calculateMonthlyBillings() {
        LocalDate currentDate = simulatedTimeService.getSimulatedDate();
        System.out.println("Naliczanie należności dla daty: " + currentDate);

        if (currentDate.getDayOfMonth() == 1) {
            List<Abonament> abonamenty = abonamentRepository.findAll();
            abonamenty.forEach(abonament -> {
                Naleznosci naleznosci = new Naleznosci();
                naleznosci.setAbonament(abonament);
                naleznosci.setTerminPlatnosci(currentDate.plusDays(5));
                naleznosci.setKwotaDoZaplaty(BigDecimal.valueOf(99.99));
                abonament.getNaleznosci().add(naleznosci);
                abonamentRepository.save(abonament);
                System.out.println("Naliczono opłatę dla abonamentu: " + abonament.getId());
            });
        }
    }
}
