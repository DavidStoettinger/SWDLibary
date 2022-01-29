package at.fh.winb.swd.libary.services;


import at.fh.winb.swd.libary.dto.AusleiheDTO;
import at.fh.winb.swd.libary.dto.ReservierungenDTO;
import at.fh.winb.swd.libary.dto.base.PagedResultDTO;
import at.fh.winb.swd.libary.dto.base.SimpleNamedDTO;
import at.fh.winb.swd.libary.entity.Ausleihe;
import at.fh.winb.swd.libary.entity.Exemplar;
import at.fh.winb.swd.libary.entity.Kunde;
import at.fh.winb.swd.libary.entity.Reservierungen;
import at.fh.winb.swd.libary.respository.AusleiheRepository;
import at.fh.winb.swd.libary.searchRequest.base.SearchRequest;
import at.fh.winb.swd.libary.services.interfaces.AusleiheService;
import at.fh.winb.swd.libary.services.interfaces.ExemplarService;
import at.fh.winb.swd.libary.services.interfaces.KundeService;
import at.fh.winb.swd.libary.services.interfaces.ReservierungService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AusleiheServiceImpl implements AusleiheService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AusleiheRepository repository;
    private final KundeService kundenService;
    private final ExemplarService exemplarService;
    private final ReservierungService reservierungService;

    @Autowired
    public AusleiheServiceImpl(AusleiheRepository repository, final KundeService kundenService, final ExemplarService exemplarService, final ReservierungService reservierungService) {
        this.repository = repository;
        this.kundenService = kundenService;
        this.exemplarService = exemplarService;
        this.reservierungService = reservierungService;
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
        if (ausleiheDTO.getId() != null) {
            logger.warn("Duplicate ID on creation request: " + ausleiheDTO.getId());
            return null;
        }

        final Ausleihe entity = new Ausleihe();
        return saveEntity(convertToEntity(ausleiheDTO, entity));
    }

    @Override
    public AusleiheDTO update(Long id, AusleiheDTO ausleiheDTO) {
        final Optional<Ausleihe> optionalAusleihe = repository.findById(id);
        if (optionalAusleihe.isEmpty())
            return null;

        final Ausleihe entity = convertToEntity(ausleiheDTO, optionalAusleihe.get());
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

    @Override
    public AusleiheDTO createInstant(String kundenID, String exemplarID,String reservierungID, AusleiheDTO ausleiheDTO) {
        Exemplar e = exemplarService.getExemplar(Long.valueOf(exemplarID));
        Kunde k = kundenService.getKunde(Long.valueOf(exemplarID));
         Reservierungen r = reservierungService.getReservierungen(Long.valueOf(reservierungID));

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_YEAR, 14);

        ausleiheDTO.setExemplar(SimpleNamedDTO.with(e.getId(), String.valueOf(e.getBibliothek()), String.valueOf(e.getMedien()), String.valueOf(e.getAusgeliehen())));
        ausleiheDTO.setKunde(SimpleNamedDTO.with(e.getId(), String.valueOf(e.getBibliothek()), String.valueOf(e.getMedien()), String.valueOf(e.getAusgeliehen())));
        ausleiheDTO.setReservierungen(SimpleNamedDTO.with(r.getId(), String.valueOf(r.getAusleihe()), String.valueOf(r.getMedien()), String.valueOf(r.getKunde())));
        ausleiheDTO.setZeitpunkt(date);
        ausleiheDTO.setSollZeit(c.getTime());
        Ausleihe a = new Ausleihe();
        return saveEntity(convertToEntity(ausleiheDTO,a));
    }

    @Override
    public double returnExemplar(String exe_id) {
        final SearchRequest searchRequest = new SearchRequest(0, 100, null);
        List<AusleiheDTO> ausleiheDTOList =
                list(searchRequest).getElements().stream().filter(a -> a.getExemplar().getId().equals(Long.valueOf(exe_id))).collect(Collectors.toList());

        if(ausleiheDTOList.isEmpty())
            return 0;
        AusleiheDTO ausleiheDTO = ausleiheDTOList.stream().filter(a -> a.getIstZeit() == null).collect(Collectors.toList()).get(0);

        Date date = new Date();
        ausleiheDTO.setIstZeit(date);


        return calculateFee(update(ausleiheDTO.getId(),ausleiheDTO));
    }

    private double calculateFee(AusleiheDTO ausleiheDTO){
        if(ausleiheDTO.getIstZeit().before(ausleiheDTO.getSollZeit()) || ausleiheDTO.getIstZeit().equals(ausleiheDTO.getSollZeit()))
            return 0;
        Long days = Duration.between(ausleiheDTO.getSollZeit().toInstant(),ausleiheDTO.getIstZeit().toInstant()).toDays();
        double price = 0.05;
        return price * days.intValue();
    }

    private AusleiheDTO convertToDTO(final Ausleihe entity) {
        final AusleiheDTO dto = new AusleiheDTO();
        dto.setId(entity.getId());
        dto.setZeitpunkt(entity.getZeitpunkt());
        dto.setIstZeit(entity.getIstZeit());
        dto.setSollZeit(entity.getSollZeit());

        final Kunde k = entity.getKunde();
        dto.setKunde(SimpleNamedDTO.with(k.getId(), k.getName()));
        final Exemplar e = entity.getExemplar();
        dto.setExemplar(SimpleNamedDTO.with(e.getId(), String.valueOf(e.getBibliothek()), String.valueOf(e.getMedien()), String.valueOf(e.getAusgeliehen())));
        final Reservierungen r = entity.getReservierungen();
        if (r != null) {
            dto.setReservierungen(SimpleNamedDTO.with(r.getId(), String.valueOf(r.getAusleihe()), String.valueOf(r.getMedien()), String.valueOf(r.getKunde())));
        }
        return dto;
    }

    private Ausleihe convertToEntity(final AusleiheDTO dto, final Ausleihe entity) {
        entity.setId(dto.getId());
        entity.setZeitpunkt(dto.getZeitpunkt());
        entity.setIstZeit(dto.getIstZeit());
        entity.setSollZeit(dto.getSollZeit());
        /*
        entity.setKunde(dto.getKunde());
        entity.setExemplar(dto.getExemplar());
        entity.setReservierungen(dto.getReservierungen());
         */
        entity.setReservierungen(reservierungService.getReservierungen(dto.getReservierungen().getId()));
        entity.setExemplar(exemplarService.getExemplar(dto.getExemplar().getId()));
        entity.setKunde(kundenService.getKunde(dto.getKunde().getId()));

        return entity;
    }

    private AusleiheDTO saveEntity(final Ausleihe entity) {
        return convertToDTO(repository.save(entity));
    }

    private Pageable getPageableFromSearchRequest(final SearchRequest searchRequest) {
        return PageRequest.of(searchRequest.getPage(), searchRequest.getPageSize());
    }
}