package at.fh.winb.swd.libary.services;

import at.fh.winb.swd.libary.dto.BibliothekDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.entity.Bibliothek;
import at.fh.winb.swd.libary.respository.BibliothekRepository;
import at.fh.winb.swd.libary.searchRequest.SearchRequest;
import at.fh.winb.swd.libary.services.interfaces.BibliothekService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BibliothekServiceImpl implements BibliothekService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final BibliothekRepository repository;


    @Autowired
    public BibliothekServiceImpl(BibliothekRepository repository) {
        this.repository = repository;
    }


    @Override
    public BibliothekDTO get(Long id) {
        return repository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public PagedResultDTO<BibliothekDTO> list(SearchRequest searchRequest) {
        final Page<Bibliothek> pagedResult = repository.findAll(getPageableFromSearchRequest(searchRequest));
        return pagedResult.stream().map(this::convertToDTO).collect(PagedResultDTO.toPagedResultDTO(pagedResult.getTotalElements()));
    }

    @Override
    public BibliothekDTO create(BibliothekDTO bibliothekDTO) {
        if(bibliothekDTO.getId() != null){
            logger.warn("Duplicate ID on creation request: "+bibliothekDTO.getId());
            return null;
        }

        final Bibliothek entity = new Bibliothek();
        return saveEntity(convertToEntity(bibliothekDTO,entity));
    }

    @Override
    public BibliothekDTO update(Long id, BibliothekDTO bibliothekDTO) {
        final Optional<Bibliothek> optionalBibliothek = repository.findById(id);
        if(optionalBibliothek.isEmpty())
            return null;

        final Bibliothek entity = convertToEntity(bibliothekDTO,optionalBibliothek.get());
        return convertToDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Bibliothek getBibliothek(Long id) {
        return repository.findById(id).orElse(null);
    }


    private BibliothekDTO convertToDTO(final Bibliothek entity){
        final BibliothekDTO dto = new BibliothekDTO();
        dto.setId(entity.getId());
        dto.setAddresse(entity.getAddresse());
        return dto;
    }

    private Bibliothek convertToEntity(final BibliothekDTO dto, final Bibliothek entity){
        entity.setAddresse(dto.getAddresse());
        return entity;
    }

    private BibliothekDTO saveEntity(final Bibliothek entity){
        return convertToDTO(repository.save(entity));
    }

    private Pageable getPageableFromSearchRequest(final SearchRequest searchRequest){
        return PageRequest.of(searchRequest.getPage(),searchRequest.getPageSize());
    }
}
