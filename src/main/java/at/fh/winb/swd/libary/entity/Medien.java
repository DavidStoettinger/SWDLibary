package at.fh.winb.swd.libary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Medien {


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

    private int FSK;
    private String Name;
    private String Beschreibung;

    @Column
    public int getFSK() {
        return FSK;
    }

    public void setFSK(int FSK) {
        this.FSK = FSK;
    }

    @Column
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Column
    public String getBeschreibung() {
        return Beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        Beschreibung = beschreibung;
    }
}
