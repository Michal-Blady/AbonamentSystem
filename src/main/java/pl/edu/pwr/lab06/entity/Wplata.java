package pl.edu.pwr.lab06.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Wplata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate terminWplaty;
    private BigDecimal kwotaWplaty;

    @ManyToOne
    @JoinColumn(name = "abonament_id")
    private Abonament abonament;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTerminWplaty() {
        return terminWplaty;
    }

    public void setTerminWplaty(LocalDate terminWplaty) {
        this.terminWplaty = terminWplaty;
    }

    public BigDecimal getKwotaWplaty() {
        return kwotaWplaty;
    }

    public void setKwotaWplaty(BigDecimal kwotaWplaty) {
        this.kwotaWplaty = kwotaWplaty;
    }

    public Abonament getAbonament() {
        return abonament;
    }

    public void setAbonament(Abonament abonament) {
        this.abonament = abonament;
    }

    public Wplata() {
    }

    public Wplata(Long id, LocalDate terminWplaty, BigDecimal kwotaWplaty, Abonament abonament) {
        this.id = id;
        this.terminWplaty = terminWplaty;
        this.kwotaWplaty = kwotaWplaty;
        this.abonament = abonament;
    }

}
