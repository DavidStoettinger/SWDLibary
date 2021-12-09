package at.fh.winb.swd.libary.dto;

import at.fh.winb.swd.libary.dto.base.BaseDTO;
import at.fh.winb.swd.libary.dto.base.SimpleNamedDTO;


public class ExemplarDTO extends BaseDTO {
    private Boolean ausgeliehen;
    private SimpleNamedDTO medien;
    private SimpleNamedDTO bibliothek;


    public Boolean getAusgeliehen() {
        return ausgeliehen;
    }

    public void setAusgeliehen(Boolean ausgeliehen) {
        this.ausgeliehen = ausgeliehen;
    }

    public SimpleNamedDTO getMedien() {
        return medien;
    }

    public void setMedien(SimpleNamedDTO medien) {
        this.medien = medien;
    }

    public SimpleNamedDTO getBibliothek() {
        return bibliothek;
    }

    public void setBibliothek(SimpleNamedDTO bibliothek) {
        this.bibliothek = bibliothek;
    }
}
