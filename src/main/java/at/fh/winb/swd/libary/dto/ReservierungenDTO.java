package at.fh.winb.swd.libary.dto;

import at.fh.winb.swd.libary.dto.base.BaseDTO;
import at.fh.winb.swd.libary.dto.base.SimpleNamedDTO;

import java.util.Date;

public class ReservierungenDTO extends BaseDTO {
    private Date Ausleihe;
    private Date Zeitpunkt;
    private SimpleNamedDTO medien;
    private SimpleNamedDTO kunde;

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

    public SimpleNamedDTO getMedien() {
        return medien;
    }

    public void setMedien(SimpleNamedDTO medien) {
        this.medien = medien;
    }

    public SimpleNamedDTO getKunde() {
        return kunde;
    }

    public void setKunde(SimpleNamedDTO kunde) {
        this.kunde = kunde;
    }
}
