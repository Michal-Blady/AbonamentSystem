package pl.edu.pwr.lab06.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Abonament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypAbonamentu typAbonamentu;

    @ManyToOne
    @JoinColumn(name = "klient_id")
    private Klient klient;

    @OneToMany(mappedBy = "abonament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subkonto> subkonta = new ArrayList<>();

    @OneToMany(mappedBy = "abonament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Naleznosci> naleznosci = new ArrayList<>();

    @OneToMany(mappedBy = "abonament", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Wplata> wplaty = new ArrayList<>();

    public Abonament() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypAbonamentu getTypAbonamentu() {
        return typAbonamentu;
    }

    public void setTypAbonamentu(TypAbonamentu typAbonamentu) {
        this.typAbonamentu = typAbonamentu;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }

    public List<Subkonto> getSubkonta() {
        return subkonta;
    }

    public void setSubkonta(List<Subkonto> subkonta) {
        this.subkonta = subkonta;
    }

    public List<Naleznosci> getNaleznosci() {
        return naleznosci;
    }

    public void setNaleznosci(List<Naleznosci> naleznosci) {
        this.naleznosci = naleznosci;
    }

    public List<Wplata> getWplaty() {
        return wplaty;
    }

    public void setWplaty(List<Wplata> wplaty) {
        this.wplaty = wplaty;
    }

    public Abonament(Long id, TypAbonamentu typAbonamentu, Klient klient, List<Subkonto> subkonta, List<Naleznosci> naleznosci, List<Wplata> wplaty) {
        this.id = id;
        this.typAbonamentu = typAbonamentu;
        this.klient = klient;
        this.subkonta = subkonta;
        this.naleznosci = naleznosci;
        this.wplaty = wplaty;
    }
}
