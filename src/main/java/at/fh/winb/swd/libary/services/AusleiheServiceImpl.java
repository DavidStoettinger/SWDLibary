package at.fh.winb.swd.libary.services;


import at.fh.winb.swd.libary.dto.AusleiheDTO;
import at.fh.winb.swd.libary.dto.BibliothekDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.entity.Ausleihe;
import at.fh.winb.swd.libary.entity.Bibliothek;
import at.fh.winb.swd.libary.respository.AusleiheRepository;
import at.fh.winb.swd.libary.respository.BibliothekRepository;
import at.fh.winb.swd.libary.searchRequest.SearchRequest;
import at.fh.winb.swd.libary.services.interfaces.AusleiheService;
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
public class AusleiheServiceImpl implements AusleiheService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AusleiheRepository repository;


    @Autowired
    public AusleiheServiceImpl(AusleiheRepository repository) {
        this.repository = repository;
    }


    @Override
    public AusleiheDTO get(Long id) {
        return repository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public PagedResultDTO<AusleiheDTO> list(SearchRequest searchRequest) {
        final Page<Ausleihe> pagedResult = repository.findAll(getPageableFromSearchRequest(searchRequest));
        return pagedResult.stream().map(this::convertToDTO).collect(PagedResultDTO.toPagedResultDTO(pagedResult.getTotalElements()));
    }

    @Override
    public AusleiheDTO create(AusleiheDTO ausleiheDTO) {
        if(ausleiheDTO.getId() != null){
            logger.warn("Duplicate ID on creation request: "+ausleiheDTO.getId());
            return null;
        }

        final Ausleihe entity = new Ausleihe();
        return saveEntity(convertToEntity(ausleiheDTO,entity));
    }

    @Override
    public AusleiheDTO update(Long id, AusleiheDTO ausleiheDTO) {
        final Optional<Ausleihe> optionalAusleihe = repository.findById(id);
        if(optionalAusleihe.isEmpty())
            return null;

        final Ausleihe entity = convertToEntity(ausleiheDTO,optionalAusleihe.get());
        return convertToDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Ausleihe getAusleihe(Long id) {
        return repository.findById(id).orElse(null);
    }


    private AusleiheDTO convertToDTO(final Ausleihe entity){
        final AusleiheDTO dto = new AusleiheDTO();
        dto.setId(entity.getId());
        dto.setZeitpunkt(entity.getZeitpunkt());
        dto.setIstZeit(entity.getIstZeit());
        dto.setSollZeit(entity.getSollZeit());
        /*
        dto.setKunde(entity.getKunde());
        dto.setExemplar(entity.getExemplar());
        dto.setReservierungen(entity.getReservierungen());
         */
        return dto;
    }

    private Ausleihe convertToEntity(final AusleiheDTO dto, final Ausleihe entity){
        entity.setId(dto.getId());
        entity.setZeitpunkt(dto.getZeitpunkt());
        entity.setIstZeit(dto.getIstZeit());
        entity.setSollZeit(dto.getSollZeit());
        /*
        entity.setKunde(dto.getKunde());
        entity.setExemplar(dto.getExemplar());
        entity.setReservierungen(dto.getReservierungen());
         */
        return entity;
    }

    private AusleiheDTO saveEntity(final Ausleihe entity){
        return convertToDTO(repository.save(entity));
    }

    private Pageable getPageableFromSearchRequest(final SearchRequest searchRequest){
        return PageRequest.of(searchRequest.getPage(),searchRequest.getPageSize());
    }
}