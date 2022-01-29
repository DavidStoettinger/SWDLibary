package at.fh.winb.swd.libary.services.interfaces;

import at.fh.winb.swd.libary.api.AusleiheApi;
import at.fh.winb.swd.libary.dto.AusleiheDTO;
import at.fh.winb.swd.libary.entity.Ausleihe;
import at.fh.winb.swd.libary.entity.Bibliothek;
import org.springframework.stereotype.Service;


public interface AusleiheService extends AusleiheApi {
    Ausleihe getAusleihe(Long id);
}
