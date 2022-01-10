package at.fh.winb.swd.libary.api;

import at.fh.winb.swd.libary.dto.BibliothekDTO;
import at.fh.winb.swd.libary.dto.ExemplarDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.searchRequest.SearchRequest;

public interface ExemplarApi {
    ExemplarDTO get(Long id);

    PagedResultDTO<BibliothekDTO> list(SearchRequest searchRequest);

    ExemplarDTO create(ExemplarDTO exemplarDTO);

    ExemplarDTO update(Long id, ExemplarDTO exemplarDTO);

    void delete(Long id);
}
