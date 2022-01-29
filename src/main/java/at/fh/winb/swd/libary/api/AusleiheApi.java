package at.fh.winb.swd.libary.api;

import at.fh.winb.swd.libary.dto.AusleiheDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.searchRequest.base.SearchRequest;

public interface AusleiheApi {
    AusleiheDTO get(Long id);

    PagedResultDTO<AusleiheDTO> list(SearchRequest searchRequest);

    AusleiheDTO create(AusleiheDTO ausleiheDTO);

    AusleiheDTO update(Long id, AusleiheDTO ausleiheDTO);

    void delete(Long id);
}
