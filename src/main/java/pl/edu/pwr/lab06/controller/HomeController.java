package pl.edu.pwr.lab06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.edu.pwr.lab06.service.SimulatedTimeService;

@Controller
public class HomeController {

    private final SimulatedTimeService simulatedTimeService;

    @Autowired
    public HomeController(SimulatedTimeService simulatedTimeService) {
        this.simulatedTimeService = simulatedTimeService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("simulatedDate", simulatedTimeService.getSimulatedDate());
        return "login";
    }
}
