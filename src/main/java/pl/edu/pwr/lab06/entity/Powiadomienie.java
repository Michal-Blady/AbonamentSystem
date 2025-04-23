package pl.edu.pwr.lab06.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Powiadomienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tresc;
    private LocalDate dataUtworzenia;

    @ManyToOne
    @JoinColumn(name = "klient_id")
    private Klient klient;

    public Powiadomienie() {
    }

    public Powiadomienie(String tresc, LocalDate dataUtworzenia, Klient klient) {
        this.tresc = tresc;
        this.dataUtworzenia = dataUtworzenia;
        this.klient = klient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public LocalDate getDataUtworzenia() {
        return dataUtworzenia;
    }

    public void setDataUtworzenia(LocalDate dataUtworzenia) {
        this.dataUtworzenia = dataUtworzenia;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }
}
