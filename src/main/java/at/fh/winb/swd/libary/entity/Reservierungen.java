package at.fh.winb.swd.libary.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Reservierungen {

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

    private Date Ausleihe;
    private Date Zeitpunkt;

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
}
