package at.fh.winb.swd.libary.services.interfaces;

import at.fh.winb.swd.libary.api.KundenApi;
import at.fh.winb.swd.libary.entity.Kunde;

public interface KundeService extends KundenApi {

    Kunde getKunde(Long id);
}
