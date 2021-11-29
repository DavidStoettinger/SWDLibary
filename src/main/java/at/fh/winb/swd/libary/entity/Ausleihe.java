package at.fh.winb.swd.libary.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Ausleihe {
    //region id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    //endregion

    private Date Zeitpunkt;
    private Date IstZeit;
    private Date SollZeit;

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

}
