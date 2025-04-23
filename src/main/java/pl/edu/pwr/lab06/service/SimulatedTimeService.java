package pl.edu.pwr.lab06.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SimulatedTimeService {
    private LocalDate simulatedDate = LocalDate.of(2025, 4, 1);

    @Scheduled(fixedRate = 1000)
    public void updateSimulatedDate() {
        simulatedDate = simulatedDate.plusDays(1);
        System.out.println("Aktualna data symulowana: " + simulatedDate);
    }

    public LocalDate getSimulatedDate() {
        return simulatedDate;
    }

    public void resetSimulatedDate(LocalDate newStartDate) {
        this.simulatedDate = newStartDate;
    }

}

