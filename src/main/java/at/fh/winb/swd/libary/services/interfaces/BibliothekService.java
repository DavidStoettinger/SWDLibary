package at.fh.winb.swd.libary.services.interfaces;

import at.fh.winb.swd.libary.api.BibliothekApi;
import at.fh.winb.swd.libary.entity.Bibliothek;
import at.fh.winb.swd.libary.entity.Medien;
import org.springframework.stereotype.Service;


public interface BibliothekService extends BibliothekApi {
    Bibliothek getBibliothek(Long id);
}
