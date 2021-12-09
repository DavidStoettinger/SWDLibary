package at.fh.winb.swd.libary.dto;

import at.fh.winb.swd.libary.dto.base.BaseDTO;

import javax.persistence.Column;

public class KundenDTO extends BaseDTO {
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
