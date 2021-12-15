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

    private Date Zeitpunkt;
    private Date IstZeit;
    private Date SollZeit;

    private Kunde kunde;
    private Exemplar exemplar;
    private Reservierungen reservierungen;

    @Column
    public Date getZeitpunkt() {
        return Zeitpunkt;
    }

    public void setZeitpunkt(Date zeitpunkt) {
        Zeitpunkt = zeitpunkt;
    }

    @Column
    public Date getIstZeit() {
        return IstZeit;
    }

    public void setIstZeit(Date istZeit) {
        IstZeit = istZeit;
    }

    @Column
    public Date getSollZeit() {
        return SollZeit;
    }

    public void setSollZeit(Date sollZeit) {
        SollZeit = sollZeit;
    }

    @ManyToOne
    public Kunde getKunde() {
        return kunde;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    @ManyToOne
    public Exemplar getExemplar() {
        return exemplar;
    }

    public void setExemplar(Exemplar exemplar) {
        this.exemplar = exemplar;
    }

    @OneToOne
    public Reservierungen getReservierungen() {
        return reservierungen;
    }

    public void setReservierungen(Reservierungen reservierungen) {
        this.reservierungen = reservierungen;
    }
}
