package at.fh.winb.swd.libary.api;

import at.fh.winb.swd.libary.dto.AusleiheDTO;
import at.fh.winb.swd.libary.dto.ReservierungenDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.searchRequest.base.SearchRequest;

public interface AusleiheApi {
    AusleiheDTO get(Long id);

    PagedResultDTO<AusleiheDTO> list(SearchRequest searchRequest);

    AusleiheDTO create(AusleiheDTO ausleiheDTO);

    AusleiheDTO update(Long id, AusleiheDTO ausleiheDTO);

    AusleiheDTO createInstant(String kundenID, String exemplarID,String reservierungID,AusleiheDTO ausleiheDTO);

    AusleiheDTO returnExemplar(String exe_id);

    void delete(Long id);
}
