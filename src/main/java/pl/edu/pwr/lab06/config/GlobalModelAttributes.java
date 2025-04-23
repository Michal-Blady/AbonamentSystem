package pl.edu.pwr.lab06.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.edu.pwr.lab06.service.SimulatedTimeService;

import java.time.LocalDate;

@ControllerAdvice
public class GlobalModelAttributes {

    private final SimulatedTimeService simulatedTimeService;

    public GlobalModelAttributes(SimulatedTimeService simulatedTimeService) {
        this.simulatedTimeService = simulatedTimeService;
    }

    @ModelAttribute("simulatedDate")
    public LocalDate addSimulatedDateToModel() {
        return simulatedTimeService.getSimulatedDate();
    }
}

