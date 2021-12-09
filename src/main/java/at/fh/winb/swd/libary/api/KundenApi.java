package at.fh.winb.swd.libary.api;

import at.fh.winb.swd.libary.dto.KundenDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.searchRequest.SearchRequest;

public interface KundenApi {

    KundenDTO get(Long id);

    PagedResultDTO<KundenDTO> list(SearchRequest searchRequest);

    KundenDTO create(KundenDTO kundenDTO);

    KundenDTO update(Long id, KundenDTO kundenDTO);

    void delete(Long id);
}
