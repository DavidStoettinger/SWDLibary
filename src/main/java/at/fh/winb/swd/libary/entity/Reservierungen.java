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

    @Column
    private Date Ausleihe; //Zeitpunkt bis wann die ausleihe erfolgen muss
    @Column
    private Date Zeitpunkt; //Zeitpunkt der reservierung
    @ManyToOne
    private Medien medien;
    @ManyToOne
    private Kunde kunde;


    public Date getAusleihe() {
        return Ausleihe;
    }

    public void setAusleihe(Date ausleihe) {
        Ausleihe = ausleihe;
    }


    public Date getZeitpunkt() {
        return Zeitpunkt;
    }

    public void setZeitpunkt(Date zeitpunkt) {
        Zeitpunkt = zeitpunkt;
    }



    public Medien getMedien() {
        return medien;
    }

    public void setMedien(Medien medien) {
        this.medien = medien;
    }


    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }
}
