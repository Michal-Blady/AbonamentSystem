package pl.edu.pwr.lab06.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@RequestMapping("/admin")
public class AdminController {

    private final KlientRepository klientRepository;
    private final NaleznosciRepository naleznosciRepository;
    private final WplataRepository wplataRepository;
    private final CennikRepository cennikRepository;
    private final AbonamentRepository abonamentRepository;
    private final SimulatedTimeService simulatedTimeService;
    private final PowiadomienieRepository powiadomienieRepository;
    private final SubkontoRepository subkontoRepository;


    @Autowired
    public AdminController(KlientRepository klientRepository,
                           NaleznosciRepository naleznosciRepository,
                           WplataRepository wplataRepository,
                           CennikRepository cennikRepository,
                           AbonamentRepository abonamentRepository,
                           SimulatedTimeService simulatedTimeService,
                            PowiadomienieRepository powiadomienieRepository,
                           SubkontoRepository subkontoRepository) {
        this.klientRepository = klientRepository;
        this.naleznosciRepository = naleznosciRepository;
        this.wplataRepository = wplataRepository;
        this.cennikRepository = cennikRepository;
        this.abonamentRepository = abonamentRepository;
        this.simulatedTimeService = simulatedTimeService;
        this.powiadomienieRepository = powiadomienieRepository;
        this.subkontoRepository = subkontoRepository;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("clientsCount", klientRepository.count());
        model.addAttribute("billingsCount", naleznosciRepository.count());
        model.addAttribute("paymentsCount", wplataRepository.count());
        model.addAttribute("abonamentsCount", abonamentRepository.count());

        List<Powiadomienie> last10 = powiadomienieRepository
                .findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "dataUtworzenia")))
                .getContent();
        model.addAttribute("notifications", last10);
        return "admin/dashboard";
    }

    @GetMapping("/clients")
    public String adminClients(@RequestParam(required = false) String imie,
                               @RequestParam(required = false) String nazwisko,
                               Model model) {
        List<Klient> clients;
        if ((imie == null || imie.isEmpty()) && (nazwisko == null || nazwisko.isEmpty())) {
            clients = klientRepository.findAll();
        } else {
            clients = klientRepository.findByImieContainingIgnoreCaseAndNazwiskoContainingIgnoreCase(imie, nazwisko);
        }
        model.addAttribute("clients", clients);
        return "admin/clients";
    }

    @GetMapping("/clients/{id}")
    public String adminClientDetails(@PathVariable Long id, Model model) {
        Optional<Klient> opt = klientRepository.findById(id);
        if (opt.isPresent()) {
            model.addAttribute("client", opt.get());
            return "admin/client-details";
        }
        return "redirect:/admin/clients";
    }


    @GetMapping("/billings")
    public String adminBillings(@RequestParam(required = false) String imie,
                                @RequestParam(required = false) String nazwisko,
                                @RequestParam(required = false) Boolean oplacone,
                                Model model) {
        List<Naleznosci> billings = naleznosciRepository.findAll();
        model.addAttribute("billings", billings);
        return "admin/billings";
    }

    @GetMapping("/payments")
    public String adminPayments(Model model) {
        List<Wplata> payments = wplataRepository.findAll();
        model.addAttribute("payments", payments);
        return "admin/payments";
    }

    @GetMapping("/pricelist")
    public String adminPricelist(Model model) {
        List<Cennik> pricelist = cennikRepository.findAll();
        model.addAttribute("pricelist", pricelist);
        return "admin/pricelist";
    }


    @GetMapping("/notifications")
    public String clientNotifications(Model model, Principal principal) {
        List<Powiadomienie> notifications = powiadomienieRepository.findAll();
        model.addAttribute("notifications", notifications);
        return "admin/notifications";
    }

    @GetMapping("/abonaments")
    public String adminAbonaments(Model model) {
        List<Abonament> abonaments = abonamentRepository.findAll();
        model.addAttribute("abonaments", abonaments);
        return "admin/abonaments";
    }

    @GetMapping("/add-billing")
    public String addBillingForm(Model model) {
        model.addAttribute("billing", new Naleznosci());
        return "admin/add-billing";
    }

    @PostMapping("/add-billing")
    public String processAddBilling(@ModelAttribute Naleznosci billing) {
        naleznosciRepository.save(billing);
        return "redirect:/admin/billings";
    }

    @GetMapping("/edit-billing/{id}")
    public String editBillingForm(@PathVariable Long id, Model model) {
        Optional<Naleznosci> billing = naleznosciRepository.findById(id);
        if (billing.isPresent()) {
            model.addAttribute("billing", billing.get());
            return "admin/edit-billing";
        }
        return "redirect:/admin/billings";
    }

    @PostMapping("/edit-billing")
    public String processEditBilling(@ModelAttribute Naleznosci billingForm) {
        Optional<Naleznosci> existingOpt = naleznosciRepository.findById(billingForm.getId());
        if(existingOpt.isPresent()){
            Naleznosci billingOriginal = existingOpt.get();
            billingOriginal.setTerminPlatnosci(billingForm.getTerminPlatnosci());
            billingOriginal.setKwotaDoZaplaty(billingForm.getKwotaDoZaplaty());
            billingOriginal.setOplacone(billingForm.isOplacone());
            naleznosciRepository.save(billingOriginal);
        }
        return "redirect:/admin/billings";
    }

    @GetMapping("/delete-billing/{id}")
    public String deleteBilling(@PathVariable Long id) {
        naleznosciRepository.deleteById(id);
        return "redirect:/admin/billings";
    }

    @GetMapping("/add-payment")
    public String addPaymentForm(Model model) {
        model.addAttribute("payment", new Wplata());
        return "admin/add-payment";
    }

    @PostMapping("/add-payment")
    public String processAddPayment(@ModelAttribute Wplata payment) {
        wplataRepository.save(payment);
        return "redirect:/admin/payments";
    }

    @GetMapping("/edit-payment/{id}")
    public String editPaymentForm(@PathVariable Long id, Model model) {
        Optional<Wplata> paymentOpt = wplataRepository.findById(id);
        if (paymentOpt.isPresent()) {
            model.addAttribute("payment", paymentOpt.get());
            return "admin/edit-payment";
        }
        return "redirect:/admin/payments";
    }

    @PostMapping("/edit-payment")
    public String processEditPayment(@ModelAttribute Wplata payment) {
        wplataRepository.save(payment);
        return "redirect:/admin/payments";
    }

    @GetMapping("/delete-payment/{id}")
    public String deletePayment(@PathVariable Long id) {
        wplataRepository.deleteById(id);
        return "redirect:/admin/payments";
    }


    @GetMapping("/add-abonament")
    public String addAbonamentForm(Model model) {
        model.addAttribute("abonament", new Abonament());
        model.addAttribute("clients", klientRepository.findAll());
        return "admin/add-abonament";
    }

    @PostMapping("/add-abonament")
    public String processAddAbonament(@ModelAttribute Abonament abonament) {
        abonament.setKlient(klientRepository.findById(abonament.getKlient().getId()).orElse(null));
        abonamentRepository.save(abonament);
        return "redirect:/admin/abonaments";
    }

    @GetMapping("/edit-abonament/{id}")
    public String editAbonamentForm(@PathVariable Long id, Model model) {
        abonamentRepository.findById(id).ifPresent(a -> {
            model.addAttribute("abonament", a);
            model.addAttribute("clients", klientRepository.findAll());
        });
        return "admin/edit-abonament";
    }

    @PostMapping("/edit-abonament")
    public String processEditAbonament(@ModelAttribute Abonament form) {
        abonamentRepository.findById(form.getId()).ifPresent(orig -> {
            orig.setTypAbonamentu(form.getTypAbonamentu());
            orig.setKlient(klientRepository.findById(form.getKlient().getId()).orElse(null));
            abonamentRepository.save(orig);
        });
        return "redirect:/admin/abonaments";
    }

    @GetMapping("/delete-abonament/{id}")
    public String deleteAbonament(@PathVariable Long id) {
        abonamentRepository.deleteById(id);
        return "redirect:/admin/abonaments";
    }


    @GetMapping("/add-client")
    public String addClientForm(Model model) {
        model.addAttribute("client", new Klient());
        return "admin/add-client";
    }

    @PostMapping("/add-client")
    public String processAddClient(@ModelAttribute Klient client) {
        klientRepository.save(client);
        return "redirect:/admin/clients";
    }

    @GetMapping("/edit-client/{id}")
    public String editClientForm(@PathVariable Long id, Model model) {
        klientRepository.findById(id).ifPresent(c -> model.addAttribute("client", c));
        return "admin/edit-client";
    }

    @PostMapping("/edit-client")
    public String processEditClient(@ModelAttribute Klient form) {
        klientRepository.findById(form.getId()).ifPresent(orig -> {
            orig.setImie(form.getImie());
            orig.setNazwisko(form.getNazwisko());
            orig.setNumerKlienta(form.getNumerKlienta());
            klientRepository.save(orig);
        });
        return "redirect:/admin/clients";
    }

    @GetMapping("/delete-client/{id}")
    public String deleteClient(@PathVariable Long id) {
        klientRepository.deleteById(id);
        return "redirect:/admin/clients";
    }


}
