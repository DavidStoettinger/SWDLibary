package at.fh.winb.swd.libary.api;

import at.fh.winb.swd.libary.dto.MedienDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.searchRequest.SearchRequest;

public interface MedienApi {
    MedienDTO get(Long id);

    PagedResultDTO<MedienDTO> list(SearchRequest searchRequest);

    MedienDTO create(MedienDTO medienDTO);

    MedienDTO update(Long id, MedienDTO medienDTO);

    void delete(Long id);
}
