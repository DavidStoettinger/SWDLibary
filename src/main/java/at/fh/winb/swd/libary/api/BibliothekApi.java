package at.fh.winb.swd.libary.api;

import at.fh.winb.swd.libary.dto.BibliothekDTO;
import at.fh.winb.swd.libary.dto.MedienDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.searchRequest.SearchRequest;

public interface BibliothekApi {
    BibliothekDTO get(Long id);

    PagedResultDTO<BibliothekDTO> list(SearchRequest searchRequest);

    BibliothekDTO create(BibliothekDTO bibliothekDTO);

    BibliothekDTO update(Long id, BibliothekDTO bibliothekDTO);

    void delete(Long id);
}
