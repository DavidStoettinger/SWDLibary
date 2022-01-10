package at.fh.winb.swd.libary.services;

import at.fh.winb.swd.libary.dto.MedienDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.entity.Medien;
import at.fh.winb.swd.libary.respository.MedienRepository;
import at.fh.winb.swd.libary.searchRequest.SearchRequest;
import at.fh.winb.swd.libary.services.interfaces.MedienService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedienServiceImpl implements MedienService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final MedienRepository repository;


    @Autowired
    public MedienServiceImpl(MedienRepository repository) {
        this.repository = repository;
    }


    @Override
    public MedienDTO get(Long id) {
        return repository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public PagedResultDTO<MedienDTO> list(SearchRequest searchRequest) {
        final Page<Medien> pagedResult = repository.findAll(getPageableFromSearchRequest(searchRequest));
        return pagedResult.stream().map(this::convertToDTO).collect(PagedResultDTO.toPagedResultDTO(pagedResult.getTotalElements()));
    }

    @Override
    public MedienDTO create(MedienDTO medienDTO) {
        if (medienDTO.getId() != null) {
            logger.warn("Duplicate ID on creation request: " + medienDTO.getId());
            return null;
        }

        final Medien entity = new Medien();
        return saveEntity(convertToEntity(medienDTO, entity));
    }

    @Override
    public MedienDTO update(Long id, MedienDTO medienDTO) {
        final Optional<Medien> optionalMedia = repository.findById(id);
        if (optionalMedia.isEmpty())
            return null;

        final Medien entity = convertToEntity(medienDTO, optionalMedia.get());
        return convertToDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Medien getMedien(Long id) {
        return repository.findById(id).orElse(null);
    }


    private MedienDTO convertToDTO(final Medien entity) {
        final MedienDTO dto = new MedienDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setFSK(entity.getFSK());
        dto.setBeschreibung(entity.getBeschreibung());
        return dto;
    }

    private Medien convertToEntity(final MedienDTO dto, final Medien entity) {
        entity.setName(dto.getName());
        entity.setBeschreibung(dto.getBeschreibung());
        entity.setFSK(dto.getFSK());
        return entity;
    }

    private MedienDTO saveEntity(final Medien entity) {
        return convertToDTO(repository.save(entity));
    }

    private Pageable getPageableFromSearchRequest(final SearchRequest searchRequest) {
        return PageRequest.of(searchRequest.getPage(), searchRequest.getPageSize());
    }
}
