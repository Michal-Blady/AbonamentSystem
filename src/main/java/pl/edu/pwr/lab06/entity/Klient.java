package pl.edu.pwr.lab06.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Klient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;
    private String nazwisko;
    private String numerKlienta;

    @OneToMany(mappedBy = "klient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Abonament> abonamenty = new ArrayList<>();

    @OneToMany(mappedBy = "klient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Powiadomienie> powiadomienia = new ArrayList<>();

    public Klient(Long id, String imie, String nazwisko, String numerKlienta, List<Abonament> abonamenty, List<Powiadomienie> powiadomienia) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerKlienta = numerKlienta;
        this.abonamenty = abonamenty;
        this.powiadomienia = powiadomienia;
    }

    public Klient() {

    }

    public Klient(String imie, String nazwisko, String numerKlienta) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.numerKlienta = numerKlienta;
        this.abonamenty = abonamenty;
        this.powiadomienia = powiadomienia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getNumerKlienta() {
        return numerKlienta;
    }

    public void setNumerKlienta(String numerKlienta) {
        this.numerKlienta = numerKlienta;
    }

    public List<Abonament> getAbonamenty() {
        return abonamenty;
    }

    public void setAbonamenty(List<Abonament> abonamenty) {
        this.abonamenty = abonamenty;
    }

    public List<Powiadomienie> getPowiadomienia() {
        return powiadomienia;
    }

    public void setPowiadomienia(List<Powiadomienie> powiadomienia) {
        this.powiadomienia = powiadomienia;
    }
}

