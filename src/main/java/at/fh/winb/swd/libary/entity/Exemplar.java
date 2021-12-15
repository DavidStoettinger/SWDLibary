package at.fh.winb.swd.libary.entity;

import javax.persistence.*;

@Entity
public class Exemplar {

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

    private Boolean ausgeliehen;
    private Medien medien;
    private Bibliothek bibliothek;

    @Column
    public Boolean getAusgeliehen() {
        return ausgeliehen;
    }

    public void setAusgeliehen(Boolean ausgeliehen) {
        this.ausgeliehen = ausgeliehen;
    }

    @ManyToOne
    public Medien getMedien() {
        return medien;
    }

    public void setMedien(Medien medien) {
        this.medien = medien;
    }

    @ManyToOne
    public Bibliothek getBibliothek() {
        return bibliothek;
    }

    public void setBibliothek(Bibliothek bibliothek) {
        this.bibliothek = bibliothek;
    }
}
