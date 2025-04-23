package pl.edu.pwr.lab06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.lab06.entity.*;
import pl.edu.pwr.lab06.repository.*;
import pl.edu.pwr.lab06.service.SimulatedTimeService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/client")
public class ClientController {

    private final KlientRepository klientRepository;
    private final NaleznosciRepository naleznosciRepository;
    private final WplataRepository wplataRepository;
    private final CennikRepository cennikRepository;
    private final SimulatedTimeService simulatedTimeService;
    private final PowiadomienieRepository powiadomienieRepository;
    private final SubkontoRepository subkontoRepository;


    @Autowired
    public ClientController(KlientRepository klientRepository,
                            NaleznosciRepository naleznosciRepository,
                            WplataRepository wplataRepository,
                            CennikRepository cennikRepository,
                            SimulatedTimeService simulatedTimeService,
                            PowiadomienieRepository powiadomienieRepository,
                            SubkontoRepository subkontoRepository) {
        this.klientRepository = klientRepository;
        this.naleznosciRepository = naleznosciRepository;
        this.wplataRepository = wplataRepository;
        this.cennikRepository = cennikRepository;
        this.simulatedTimeService = simulatedTimeService;
        this.powiadomienieRepository = powiadomienieRepository;
        this.subkontoRepository = subkontoRepository;
    }

    @GetMapping("/dashboard")
    public String clientDashboard(Model model) {
        Klient client = klientRepository.findAll().stream().findFirst().orElse(new Klient("Jan", "Kowalski", "12345"));
        model.addAttribute("client", client);
        return "client/dashboard";
    }

    @GetMapping("/billings")
    public String clientBillings(Model model) {
        Klient client = klientRepository.findAll().stream().findFirst().orElse(null);
        if (client != null) {
            model.addAttribute("billings", client.getAbonamenty().stream().flatMap(a -> a.getNaleznosci().stream()).toList());
        }
        return "client/billings";
    }

    @GetMapping("/payments")
    public String clientPayments(Model model) {
        Klient client = klientRepository.findAll().stream().findFirst().orElse(null);
        if (client != null) {
            model.addAttribute("payments", client.getAbonamenty().stream().flatMap(a -> a.getWplaty().stream()).toList());
        }
        return "client/payments";
    }


    @GetMapping("/notifications")
    public String clientNotifications(Model model, Principal principal) {
        Subkonto subkonto = subkontoRepository.findByLogin(principal.getName()).orElse(null);
        if (subkonto == null) {
            return "redirect:/login";
        }
        Klient klient = subkonto.getAbonament().getKlient();
        List<Powiadomienie> notifications = powiadomienieRepository.findByKlientId(klient.getId());
        model.addAttribute("notifications", notifications);
        return "client/notifications";
    }

    @GetMapping("/pay-billing/{id}")
    public String payBilling(@PathVariable Long id) {
        Optional<Naleznosci> optBilling = naleznosciRepository.findById(id);
        if (optBilling.isPresent()) {
            Naleznosci billing = optBilling.get();
            if (!billing.isOplacone()) {
                billing.setOplacone(true);
                naleznosciRepository.save(billing);

                Wplata payment = new Wplata();
                payment.setAbonament(billing.getAbonament());
                payment.setTerminWplaty(simulatedTimeService.getSimulatedDate());
                payment.setKwotaWplaty(billing.getKwotaDoZaplaty());
                wplataRepository.save(payment);
            }
        }
        return "redirect:/client/billings";
    }


    @GetMapping("/abonaments")
    public String clientAbonaments(Model model, Principal principal) {
        Subkonto sub = subkontoRepository.findByLogin(principal.getName()).orElseThrow();
        Klient klient = sub.getAbonament().getKlient();
        model.addAttribute("abonaments", klient.getAbonamenty());
        return "client/abonaments";
    }

    @GetMapping("/pricelist")
    public String clientPricelist(Model model) {
        model.addAttribute("pricelist", cennikRepository.findAll());
        return "client/pricelist";
    }


}

