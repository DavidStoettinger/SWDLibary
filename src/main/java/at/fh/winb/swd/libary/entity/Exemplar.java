package at.fh.winb.swd.libary.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Exemplar {

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

    private Boolean ausgeliehen;

    public Boolean getAusgeliehen() {
        return ausgeliehen;
    }

    public void setAusgeliehen(Boolean ausgeliehen) {
        this.ausgeliehen = ausgeliehen;
    }
}
