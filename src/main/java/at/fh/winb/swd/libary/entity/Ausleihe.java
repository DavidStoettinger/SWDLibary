package at.fh.winb.swd.libary.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Ausleihe {
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
    private Date Zeitpunkt;

    //Rückgabezeitpunkte
    @Column(name = "istzeit")
    private Date IstZeit;
    @Column(name = "sollzeit")
    private Date SollZeit;

    @ManyToOne
    private Kunde kunde;
    @ManyToOne
    private Exemplar exemplar;
    @OneToOne
    private Reservierungen reservierungen;


    public Date getZeitpunkt() {
        return Zeitpunkt;
    }

    public void setZeitpunkt(Date zeitpunkt) {
        Zeitpunkt = zeitpunkt;
    }


    public Date getIstZeit() {
        return IstZeit;
    }

    public void setIstZeit(Date istZeit) {
        IstZeit = istZeit;
    }


    public Date getSollZeit() {
        return SollZeit;
    }

    public void setSollZeit(Date sollZeit) {
        SollZeit = sollZeit;
    }


    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }


    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }


    public Reservierungen getReservierungen() {
        return reservierungen;
    }

    public void setReservierungen(Reservierungen reservierungen) {
        this.reservierungen = reservierungen;
    }
}
