package at.fh.winb.swd.libary.services;

import at.fh.winb.swd.libary.dto.ExemplarDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.dto.base.SimpleNamedDTO;
import at.fh.winb.swd.libary.entity.Bibliothek;
import at.fh.winb.swd.libary.entity.Exemplar;
import at.fh.winb.swd.libary.entity.Medien;
import at.fh.winb.swd.libary.respository.ExemplarRepository;
import at.fh.winb.swd.libary.searchRequest.SearchRequest;
import at.fh.winb.swd.libary.services.interfaces.ExemplarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExemplarServiceImpl implements ExemplarService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ExemplarRepository repository;


    @Autowired
    public ExemplarServiceImpl(ExemplarRepository repository) {
        this.repository = repository;
    }


    @Override
    public ExemplarDTO get(Long id) {
        return repository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public PagedResultDTO<ExemplarDTO> list(SearchRequest searchRequest) {
        final Page<Exemplar> pagedResult = repository.findAll(getPageableFromSearchRequest(searchRequest));
        return pagedResult.stream().map(this::convertToDTO).collect(PagedResultDTO.toPagedResultDTO(pagedResult.getTotalElements()));
    }

    @Override
    public ExemplarDTO create(ExemplarDTO exemplarDTO) {
        if(exemplarDTO.getId() != null){
            logger.warn("Duplicate ID on creation request: "+exemplarDTO.getId());
            return null;
        }

        final Exemplar entity = new Exemplar();
        return saveEntity(convertToEntity(exemplarDTO,entity));
    }

    @Override
    public ExemplarDTO update(Long id, ExemplarDTO exemplarDTO) {
        final Optional<Exemplar> optionalExemplar = repository.findById(id);
        if(optionalExemplar.isEmpty())
            return null;

        final Exemplar entity = convertToEntity(exemplarDTO,optionalExemplar.get());
        return convertToDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Exemplar getExemplar(Long id) {
        return repository.findById(id).orElse(null);
    }


    private ExemplarDTO convertToDTO(final Exemplar entity){
        final ExemplarDTO dto = new ExemplarDTO();
        dto.setId(entity.getId());
        dto.setAusgeliehen(entity.getAusgeliehen());

        final Medien medien = entity.getMedien();
        dto.setMedien(SimpleNamedDTO.with(medien.getId(),medien.getName(), String.valueOf(medien.getFSK()),medien.getBeschreibung()));
        final Bibliothek bibliothek = entity.getBibliothek();
        dto.setBibliothek(SimpleNamedDTO.with(bibliothek.getId(), bibliothek.getAddresse()));

        return dto;
    }

    private Exemplar convertToEntity(final ExemplarDTO dto, final Exemplar entity){
        entity.setId(dto.getId());
        entity.setAusgeliehen(dto.getAusgeliehen());
        /*
        entity.setMedien(dto.getMedien());
        entity.setBibliothek(dto.getBibliothek());
         */
        return entity;
    }

    private ExemplarDTO saveEntity(final Exemplar entity){
        return convertToDTO(repository.save(entity));
    }

    private Pageable getPageableFromSearchRequest(final SearchRequest searchRequest){
        return PageRequest.of(searchRequest.getPage(),searchRequest.getPageSize());
    }
}