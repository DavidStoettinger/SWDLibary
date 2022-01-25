package at.fh.winb.swd.libary.api;

import at.fh.winb.swd.libary.dto.BibliothekDTO;
import at.fh.winb.swd.libary.dto.ReservierungenDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.searchRequest.SearchRequest;

public interface ReservierungApi {
    ReservierungenDTO get(Long id);

    PagedResultDTO<ReservierungenDTO> list(SearchRequest searchRequest);

    ReservierungenDTO create(ReservierungenDTO reservierungenDTO);

    ReservierungenDTO update(Long id, ReservierungenDTO reservierungenDTO);

    void delete(Long id);
}
