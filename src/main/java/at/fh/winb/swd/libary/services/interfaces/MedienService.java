package at.fh.winb.swd.libary.services.interfaces;

import at.fh.winb.swd.libary.api.MedienApi;
import at.fh.winb.swd.libary.entity.Medien;

public interface MedienService extends MedienApi {
    Medien getMedien(Long id);
}
