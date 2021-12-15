package at.fh.winb.swd.libary.entity;

import javax.persistence.*;

@Entity
public class Medien {


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
    private int FSK;
    @Column
    private String Name;
    @Column
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
