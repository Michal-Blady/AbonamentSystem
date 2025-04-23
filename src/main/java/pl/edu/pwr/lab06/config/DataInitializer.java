package pl.edu.pwr.lab06.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.edu.pwr.lab06.entity.*;
import pl.edu.pwr.lab06.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final KlientRepository klientRepository;
    private final AbonamentRepository abonamentRepository;
    private final NaleznosciRepository naleznosciRepository;
    private final WplataRepository wplataRepository;
    private final CennikRepository cennikRepository;
    private final SubkontoRepository subkontoRepository;
    public DataInitializer(KlientRepository klientRepository,
                           AbonamentRepository abonamentRepository,
                           NaleznosciRepository naleznosciRepository,
                           WplataRepository wplataRepository,
                           CennikRepository cennikRepository,
                           SubkontoRepository subkontoRepository) {
        this.klientRepository = klientRepository;
        this.abonamentRepository = abonamentRepository;
        this.naleznosciRepository = naleznosciRepository;
        this.wplataRepository = wplataRepository;
        this.cennikRepository = cennikRepository;
        this.subkontoRepository = subkontoRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Klient klient1 = new Klient("Jan", "Kowalski", "12345");
        Klient klient2 = new Klient("Anna", "Nowak", "54321");
        Klient klient3 = new Klient("Piotr", "Zieliński", "67890");
        Klient klient4 = new Klient("Magdalena", "Wiśniewska", "11223");
        Klient klient5 = new Klient("Tomasz", "Wójcik", "44556");
        klientRepository.saveAll(List.of(klient1, klient2, klient3, klient4, klient5));

        Abonament abon1 = new Abonament();
        abon1.setTypAbonamentu(TypAbonamentu.BASIC);
        abon1.setKlient(klient1);
        abonamentRepository.save(abon1);

        Abonament abon2 = new Abonament();
        abon2.setTypAbonamentu(TypAbonamentu.PREMIUM);
        abon2.setKlient(klient2);
        abonamentRepository.save(abon2);

        Abonament abon3 = new Abonament();
        abon3.setTypAbonamentu(TypAbonamentu.VIP);
        abon3.setKlient(klient3);
        abonamentRepository.save(abon3);

        Abonament abon4 = new Abonament();
        abon4.setTypAbonamentu(TypAbonamentu.BASIC);
        abon4.setKlient(klient4);
        abonamentRepository.save(abon4);

        Abonament abon5 = new Abonament();
        abon5.setTypAbonamentu(TypAbonamentu.PREMIUM);
        abon5.setKlient(klient5);
        abonamentRepository.save(abon5);

        klient1.getAbonamenty().add(abon1);
        klient2.getAbonamenty().add(abon2);
        klient3.getAbonamenty().add(abon3);
        klient4.getAbonamenty().add(abon4);
        klient5.getAbonamenty().add(abon5);
        klientRepository.saveAll(List.of(klient1, klient2, klient3, klient4, klient5));

        for (int i = 1; i <= 3; i++) {
            Naleznosci n = new Naleznosci();
            n.setAbonament(abon1);
            n.setTerminPlatnosci(LocalDate.now().plusDays(5L * i));
            n.setKwotaDoZaplaty(BigDecimal.valueOf(99.99 + i));
            // co druga opłacona
            n.setOplacone(i % 2 == 0);
            naleznosciRepository.save(n);
        }
        for (int i = 1; i <= 2; i++) {
            Naleznosci n = new Naleznosci();
            n.setAbonament(abon2);
            n.setTerminPlatnosci(LocalDate.now().plusDays(7L * i));
            n.setKwotaDoZaplaty(BigDecimal.valueOf(149.99 + i));
            n.setOplacone(i % 2 == 0);
            naleznosciRepository.save(n);
        }
        for (int i = 1; i <= 4; i++) {
            Naleznosci n = new Naleznosci();
            n.setAbonament(abon3);
            n.setTerminPlatnosci(LocalDate.now().plusDays(10L * i));
            n.setKwotaDoZaplaty(BigDecimal.valueOf(199.99 + i));
            n.setOplacone(i % 2 != 0);
            naleznosciRepository.save(n);
        }
        for (int i = 1; i <= 2; i++) {
            Naleznosci n = new Naleznosci();
            n.setAbonament(abon4);
            n.setTerminPlatnosci(LocalDate.now().plusDays(6L * i));
            n.setKwotaDoZaplaty(BigDecimal.valueOf(89.99 + i));
            n.setOplacone(i % 2 == 0);
            naleznosciRepository.save(n);
        }
        for (int i = 1; i <= 3; i++) {
            Naleznosci n = new Naleznosci();
            n.setAbonament(abon5);
            n.setTerminPlatnosci(LocalDate.now().plusDays(8L * i));
            n.setKwotaDoZaplaty(BigDecimal.valueOf(139.99 + i));
            n.setOplacone(i % 2 == 0);
            naleznosciRepository.save(n);
        }


        for (int i = 1; i <= 2; i++) {
            Wplata w = new Wplata();
            w.setAbonament(abon1);
            w.setTerminWplaty(LocalDate.now().plusDays(i));
            w.setKwotaWplaty(BigDecimal.valueOf(99.99 + i));
            wplataRepository.save(w);
        }
        Wplata w2 = new Wplata();
        w2.setAbonament(abon2);
        w2.setTerminWplaty(LocalDate.now());
        w2.setKwotaWplaty(BigDecimal.valueOf(149.99));
        wplataRepository.save(w2);
        for (int i = 1; i <= 3; i++) {
            Wplata w = new Wplata();
            w.setAbonament(abon3);
            w.setTerminWplaty(LocalDate.now().plusDays(i));
            w.setKwotaWplaty(BigDecimal.valueOf(199.99));
            wplataRepository.save(w);
        }
        for (int i = 1; i <= 2; i++) {
            Wplata w = new Wplata();
            w.setAbonament(abon4);
            w.setTerminWplaty(LocalDate.now().plusDays(i + 1));
            w.setKwotaWplaty(BigDecimal.valueOf(89.99 + i));
            wplataRepository.save(w);
        }
        Wplata w3 = new Wplata();
        w3.setAbonament(abon5);
        w3.setTerminWplaty(LocalDate.now());
        w3.setKwotaWplaty(BigDecimal.valueOf(139.99));
        wplataRepository.save(w3);


        Cennik c1 = new Cennik();
        c1.setTypUslugi(TypAbonamentu.BASIC);
        c1.setCena(BigDecimal.valueOf(99.99));
        c1.setDataOd(LocalDate.now().minusDays(60));
        c1.setDataDo(LocalDate.now().minusDays(30));
        cennikRepository.save(c1);
        Cennik c2 = new Cennik();
        c2.setTypUslugi(TypAbonamentu.BASIC);
        c2.setCena(BigDecimal.valueOf(109.99));
        c2.setDataOd(LocalDate.now().minusDays(30));
        c2.setDataDo(null); // aktualna cena
        cennikRepository.save(c2);

        Cennik c3 = new Cennik();
        c3.setTypUslugi(TypAbonamentu.PREMIUM);
        c3.setCena(BigDecimal.valueOf(149.99));
        c3.setDataOd(LocalDate.now().minusDays(45));
        c3.setDataDo(null);
        cennikRepository.save(c3);

        Cennik c4 = new Cennik();
        c4.setTypUslugi(TypAbonamentu.VIP);
        c4.setCena(BigDecimal.valueOf(199.99));
        c4.setDataOd(LocalDate.now().minusDays(50));
        c4.setDataDo(null);
        cennikRepository.save(c4);


        if (!subkontoRepository.existsByLogin("admin")) {
            Subkonto adminSubkonto = new Subkonto();
            adminSubkonto.setLogin("admin");
            //{noop} dla testowego przechowywania haseł – normalnie encoder
            adminSubkonto.setHaslo("{noop}admin123");
            subkontoRepository.save(adminSubkonto);
            System.out.println("Utworzono konto admina: login=admin, hasło=admin123");
        }
        if (!subkontoRepository.existsByLogin("client1")) {
            Subkonto sc1 = new Subkonto();
            sc1.setLogin("client1");
            sc1.setHaslo("{noop}client123");
            sc1.setAbonament(abon1);
            subkontoRepository.save(sc1);
            System.out.println("Utworzono konto klienta: login=client1, hasło=client123");
        }
        if (!subkontoRepository.existsByLogin("client2")) {
            Subkonto sc2 = new Subkonto();
            sc2.setLogin("client2");
            sc2.setHaslo("{noop}client123");
            sc2.setAbonament(abon2);
            subkontoRepository.save(sc2);
            System.out.println("Utworzono konto klienta: login=client2, hasło=client123");
        }
        if (!subkontoRepository.existsByLogin("client3")) {
            Subkonto sc3 = new Subkonto();
            sc3.setLogin("client3");
            sc3.setHaslo("{noop}client123");
            sc3.setAbonament(abon3);
            subkontoRepository.save(sc3);
            System.out.println("Utworzono konto klienta: login=client3, hasło=client123");
        }
        if (!subkontoRepository.existsByLogin("client4")) {
            Subkonto sc4 = new Subkonto();
            sc4.setLogin("client4");
            sc4.setHaslo("{noop}client123");
            sc4.setAbonament(abon4);
            subkontoRepository.save(sc4);
            System.out.println("Utworzono konto klienta: login=client4, hasło=client123");
        }
        if (!subkontoRepository.existsByLogin("client5")) {
            Subkonto sc5 = new Subkonto();
            sc5.setLogin("client5");
            sc5.setHaslo("{noop}client123");
            sc5.setAbonament(abon5);
            subkontoRepository.save(sc5);
            System.out.println("Utworzono konto klienta: login=client5, hasło=client123");
        }

        System.out.println("Przykładowe dane zostały dodane.");
    }
}
