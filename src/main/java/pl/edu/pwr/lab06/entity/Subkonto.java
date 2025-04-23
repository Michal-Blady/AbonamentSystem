package pl.edu.pwr.lab06.entity;

import jakarta.persistence.*;

@Entity
public class Subkonto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String haslo;

    @ManyToOne
    @JoinColumn(name = "abonament_id")
    private Abonament abonament;

    public Subkonto(Long id, String login, String haslo, Abonament abonament) {
        this.id = id;
        this.login = login;
        this.haslo = haslo;
        this.abonament = abonament;
    }

    public Subkonto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public Abonament getAbonament() {
        return abonament;
    }

    public void setAbonament(Abonament abonament) {
        this.abonament = abonament;
    }
}

