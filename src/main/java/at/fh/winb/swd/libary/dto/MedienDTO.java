package at.fh.winb.swd.libary.dto;

import at.fh.winb.swd.libary.dto.base.BaseDTO;

public class MedienDTO extends BaseDTO {
    private int FSK;
    private String Name;
    private String Beschreibung;

    public int getFSK() {
        return FSK;
    }

    public void setFSK(int FSK) {
        this.FSK = FSK;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getBeschreibung() {
        return Beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        Beschreibung = beschreibung;
    }
}
