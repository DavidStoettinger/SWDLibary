package at.fh.winb.swd.libary.services;

import at.fh.winb.swd.libary.dto.KundenDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.entity.Kunde;
import at.fh.winb.swd.libary.respository.KundeRepository;
import at.fh.winb.swd.libary.searchRequest.SearchRequest;
import at.fh.winb.swd.libary.services.interfaces.KundeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class KundeServiceImpl implements KundeService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final KundeRepository repository;


    @Autowired
    public KundeServiceImpl(KundeRepository repository) {
        this.repository = repository;
    }


    @Override
    public KundenDTO get(Long id) {
        return repository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public PagedResultDTO<KundenDTO> list(SearchRequest searchRequest) {
        final Page<Kunde> pagedResult = repository.findAll(getPageableFromSearchRequest(searchRequest));
        return pagedResult.stream().map(this::convertToDTO).collect(PagedResultDTO.toPagedResultDTO(pagedResult.getTotalElements()));
    }

    @Override
    public KundenDTO create(KundenDTO kundenDTO) {
        if(kundenDTO.getId() != null){
            logger.warn("Duplicate ID on creation request: "+kundenDTO.getId());
            return null;
        }

        final  Kunde entity = new Kunde();
        Random rand = new Random(System.currentTimeMillis());
        Long tmpId = rand.nextLong()%1000000;
        entity.setId(tmpId < 0 ? tmpId*-1 : tmpId);
        return saveEntity(convertToEntity(kundenDTO,entity));
    }

    @Override
    public KundenDTO update(Long id, KundenDTO kundenDTO) {
        final Optional<Kunde> optionalKunde = repository.findById(id);
        if(optionalKunde.isEmpty())
            return null;

        final Kunde entity = convertToEntity(kundenDTO,optionalKunde.get());
        return convertToDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Kunde getKunde(Long id) {
        return repository.findById(id).orElse(null);
    }


    private KundenDTO convertToDTO(final Kunde entity){
        final KundenDTO dto = new KundenDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedTimestamp(entity.getCreatedTimestamp());
        dto.setModifiedTimestamp(entity.getModifiedTimestamp());
        return dto;
    }

    private Kunde convertToEntity(final KundenDTO dto, final Kunde entity){
        entity.setCreatedTimestamp(dto.getCreatedTimestamp());
        entity.setModifiedTimestamp(dto.getModifiedTimestamp());
        entity.setName(dto.getName());
        return entity;
    }

    private KundenDTO saveEntity(final Kunde entity){
        return convertToDTO(repository.save(entity));
    }

    private Pageable getPageableFromSearchRequest(final SearchRequest searchRequest){
        return PageRequest.of(searchRequest.getPage(),searchRequest.getPageSize());
    }
}