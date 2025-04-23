package pl.edu.pwr.lab06.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Naleznosci {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate terminPlatnosci;
    private BigDecimal kwotaDoZaplaty;
    private boolean oplacone;

    @ManyToOne
    @JoinColumn(name = "abonament_id")
    private Abonament abonament;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getTerminPlatnosci() {
        return terminPlatnosci;
    }

    public void setTerminPlatnosci(LocalDate terminPlatnosci) {
        this.terminPlatnosci = terminPlatnosci;
    }

    public BigDecimal getKwotaDoZaplaty() {
        return kwotaDoZaplaty;
    }

    public void setKwotaDoZaplaty(BigDecimal kwotaDoZaplaty) {
        this.kwotaDoZaplaty = kwotaDoZaplaty;
    }

    public Abonament getAbonament() {
        return abonament;
    }

    public void setAbonament(Abonament abonament) {
        this.abonament = abonament;
    }

    public Naleznosci() {
    }

    public boolean isOplacone() {
        return oplacone;
    }

    public void setOplacone(boolean oplacone) {
        this.oplacone = oplacone;
    }

    public Naleznosci(Long id, LocalDate terminPlatnosci, BigDecimal kwotaDoZaplaty, boolean oplacone, Abonament abonament) {
        this.id = id;
        this.terminPlatnosci = terminPlatnosci;
        this.kwotaDoZaplaty = kwotaDoZaplaty;
        this.oplacone = oplacone;
        this.abonament = abonament;
    }
}
