package at.fh.winb.swd.libary.services.interfaces;

import at.fh.winb.swd.libary.api.ExemplarApi;
import at.fh.winb.swd.libary.entity.Bibliothek;
import at.fh.winb.swd.libary.entity.Exemplar;

public interface ExemplarService extends ExemplarApi {
    Exemplar getExemplar(Long id);
}
