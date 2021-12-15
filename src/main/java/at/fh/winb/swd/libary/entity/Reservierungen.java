package at.fh.winb.swd.libary.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservierungen {

    //region id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    //endregion

    private Date Ausleihe;
    private Date Zeitpunkt;
    private Medien medien;
    private Kunde kunde;

    @Column
    public Date getAusleihe() {
        return Ausleihe;
    }

    public void setAusleihe(Date ausleihe) {
        Ausleihe = ausleihe;
    }

    @Column
    public Date getZeitpunkt() {
        return Zeitpunkt;
    }

    public void setZeitpunkt(Date zeitpunkt) {
        Zeitpunkt = zeitpunkt;
    }


    @ManyToOne
    public Medien getMedien() {
        return medien;
    }

    public void setMedien(Medien medien) {
        this.medien = medien;
    }

    @ManyToOne
    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }
}
