package pl.edu.pwr.lab06.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pl.edu.pwr.lab06.entity.Klient;
import pl.edu.pwr.lab06.entity.Naleznosci;
import pl.edu.pwr.lab06.entity.Powiadomienie;
import pl.edu.pwr.lab06.repository.NaleznosciRepository;
import pl.edu.pwr.lab06.repository.PowiadomienieRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReminderService {

    private final NaleznosciRepository naleznosciRepository;
    private final SimulatedTimeService simulatedTimeService;
    private final PowiadomienieRepository powiadomienieRepository;

    public ReminderService(NaleznosciRepository naleznosciRepository, SimulatedTimeService simulatedTimeService, PowiadomienieRepository powiadomienieRepository) {
        this.naleznosciRepository = naleznosciRepository;
        this.simulatedTimeService = simulatedTimeService;
        this.powiadomienieRepository = powiadomienieRepository;
    }

    @Scheduled(fixedRate = 1000)
    public void checkOverduePayments() {
        LocalDate currentDate = simulatedTimeService.getSimulatedDate();
        System.out.println("Sprawdzanie zaległych płatności dla daty: " + currentDate);

        List<Naleznosci> naleznosciList = naleznosciRepository.findAll();
        for (Naleznosci naleznosc : naleznosciList) {
            if (currentDate.isAfter(naleznosc.getTerminPlatnosci()) && !naleznosc.isOplacone()) {
                Klient klient = naleznosc.getAbonament().getKlient();
                String tresc = "Monit: Abonament " + naleznosc.getAbonament().getId() + " - płatność przeterminowana!";
                Powiadomienie powiadomienie = new Powiadomienie(tresc, currentDate, klient);
                powiadomienieRepository.save(powiadomienie);
                System.out.println(tresc);
            }
        }
    }
}
