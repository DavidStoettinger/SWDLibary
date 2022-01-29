package at.fh.winb.swd.libary.services;

import at.fh.winb.swd.libary.dto.ReservierungenDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.dto.base.SimpleNamedDTO;
import at.fh.winb.swd.libary.entity.Kunde;
import at.fh.winb.swd.libary.entity.Medien;
import at.fh.winb.swd.libary.entity.Reservierungen;
import at.fh.winb.swd.libary.respository.ReservierungRepository;
import at.fh.winb.swd.libary.searchRequest.base.SearchRequest;
import at.fh.winb.swd.libary.services.interfaces.KundeService;
import at.fh.winb.swd.libary.services.interfaces.MedienService;
import at.fh.winb.swd.libary.services.interfaces.ReservierungService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservierungenServiceImpl implements ReservierungService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ReservierungRepository repository;
    private final MedienService medienService;
    private final KundeService kundenService;


    @Autowired
    public ReservierungenServiceImpl(ReservierungRepository repository, MedienService medienService, KundeService kundenService) {
        this.repository = repository;
        this.medienService = medienService;
        this.kundenService = kundenService;
    }


    @Override
    public ReservierungenDTO get(Long id) {
        return repository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public PagedResultDTO<ReservierungenDTO> list(SearchRequest searchRequest) {
        final Page<Reservierungen> pagedResult = repository.findAll(getPageableFromSearchRequest(searchRequest));
        return pagedResult.stream().map(this::convertToDTO).collect(PagedResultDTO.toPagedResultDTO(pagedResult.getTotalElements()));
    }

    @Override
    public ReservierungenDTO create(ReservierungenDTO reservierungenDTO) {
        if(reservierungenDTO.getId() != null){
            logger.warn("Duplicate ID on creation request: "+reservierungenDTO.getId());
            return null;
        }

        final Reservierungen entity = new Reservierungen();
        return saveEntity(convertToEntity(reservierungenDTO,entity));
    }

    @Override
    public ReservierungenDTO update(Long id, ReservierungenDTO reservierungenDTO) {
        final Optional<Reservierungen> optionalReservierungen = repository.findById(id);
        if(optionalReservierungen.isEmpty())
            return null;

        final Reservierungen entity = convertToEntity(reservierungenDTO,optionalReservierungen.get());
        return convertToDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Reservierungen getReservierungen(Long id) {
        return repository.findById(id).orElse(null);
    }


    private ReservierungenDTO convertToDTO(final Reservierungen entity){
        final ReservierungenDTO dto = new ReservierungenDTO();
        dto.setId(entity.getId());
        dto.setAusleihe(entity.getAusleihe());
        dto.setZeitpunkt(entity.getZeitpunkt());

        final Medien medien = entity.getMedien();
        dto.setMedien(SimpleNamedDTO.with(medien.getId(),medien.getName(), String.valueOf(medien.getFSK()),medien.getBeschreibung()));
        final Kunde kunde = entity.getKunde();
        dto.setMedien(SimpleNamedDTO.with(kunde.getId(), kunde.getName()));

        return dto;
    }

    private Reservierungen convertToEntity(final ReservierungenDTO dto, final Reservierungen entity){
        entity.setId(dto.getId());
        entity.setAusleihe(dto.getAusleihe());
        entity.setZeitpunkt(dto.getZeitpunkt());

        //final Medien medien = medienService.getMedien()


        return entity;
    }

    private ReservierungenDTO saveEntity(final Reservierungen entity){
        return convertToDTO(repository.save(entity));
    }

    private Pageable getPageableFromSearchRequest(final SearchRequest searchRequest){
        return PageRequest.of(searchRequest.getPage(),searchRequest.getPageSize());
    }
}