package at.fh.winb.swd.libary.services.interfaces;

import at.fh.winb.swd.libary.api.ReservierungApi;
import at.fh.winb.swd.libary.entity.Reservierungen;

public interface ReservierungService extends ReservierungApi {
    Reservierungen getReservierungen(Long id);
}
