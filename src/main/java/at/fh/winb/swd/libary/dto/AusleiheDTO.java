package at.fh.winb.swd.libary.dto;

import at.fh.winb.swd.libary.dto.base.BaseDTO;
import at.fh.winb.swd.libary.dto.base.SimpleNamedDTO;

import java.util.Date;

public class AusleiheDTO extends BaseDTO {
    private Date Zeitpunkt;
    private Date IstZeit;
    private Date SollZeit;

    private SimpleNamedDTO kunde;
    private SimpleNamedDTO exemplar;
    private SimpleNamedDTO reservierungen;


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

    public SimpleNamedDTO getKunde() {
        return kunde;
    }

    public void setKunde(SimpleNamedDTO kunde) {
        this.kunde = kunde;
    }

    public SimpleNamedDTO getExemplar() {
        return exemplar;
    }

    public void setExemplar(SimpleNamedDTO exemplar) {
        this.exemplar = exemplar;
    }

    public SimpleNamedDTO getReservierungen() {
        return reservierungen;
    }

    public void setReservierungen(SimpleNamedDTO reservierungen) {
        this.reservierungen = reservierungen;
    }
}
