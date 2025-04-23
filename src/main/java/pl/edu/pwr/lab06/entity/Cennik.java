package pl.edu.pwr.lab06.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Cennik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypAbonamentu typUslugi;

    private BigDecimal cena;

    private LocalDate dataOd;
    private LocalDate dataDo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypAbonamentu getTypUslugi() {
        return typUslugi;
    }

    public void setTypUslugi(TypAbonamentu typUslugi) {
        this.typUslugi = typUslugi;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public LocalDate getDataOd() {
        return dataOd;
    }

    public void setDataOd(LocalDate dataOd) {
        this.dataOd = dataOd;
    }

    public LocalDate getDataDo() {
        return dataDo;
    }

    public void setDataDo(LocalDate dataDo) {
        this.dataDo = dataDo;
    }

    public Cennik() {
    }

    public Cennik(Long id, TypAbonamentu typUslugi, BigDecimal cena, LocalDate dataOd, LocalDate dataDo) {
        this.id = id;
        this.typUslugi = typUslugi;
        this.cena = cena;
        this.dataOd = dataOd;
        this.dataDo = dataDo;
    }
}
