package at.fh.winb.swd.libary.controller;

import at.fh.winb.swd.libary.api.AusleiheApi;
import at.fh.winb.swd.libary.api.ExemplarApi;
import at.fh.winb.swd.libary.api.KundenApi;
import at.fh.winb.swd.libary.dto.AusleiheDTO;
import at.fh.winb.swd.libary.dto.ExemplarDTO;
import at.fh.winb.swd.libary.dto.KundenDTO;
import at.fh.winb.swd.libary.entity.Exemplar;
import at.fh.winb.swd.libary.searchRequest.base.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/return")
public class ReturnController {

    final AusleiheApi ausleiheApi;
    final KundenApi kundenApi;
    final ExemplarApi exemplarApi;

    private final String path = "return-media";

    @Autowired
    public ReturnController(final AusleiheApi ausleiheApi, final KundenApi kundenApi, final ExemplarApi exemplarApi) {
        this.ausleiheApi = ausleiheApi;
        this.kundenApi = kundenApi;
        this.exemplarApi = exemplarApi;
    }


    @Transactional
    @GetMapping()
    public String list(final Model model) {
        final SearchRequest searchRequest = new SearchRequest(0, 100, null);
        final List<KundenDTO> element = kundenApi.list(searchRequest).getElements();

        model.addAttribute("elements", element == null ? Collections.emptyList() : element);
        model.addAttribute("totalCount", kundenApi.list(searchRequest).getTotalCount());

        return (path + "/login");
    }

    @Transactional
    @GetMapping("/{id}")
    public String get(@PathVariable final String id, final Model model) {
        final SearchRequest searchRequest = new SearchRequest(0, 100, null);
        final List<ExemplarDTO> element = exemplarApi.list(searchRequest).getElements().stream().filter(s -> s.getAusgeliehen()).collect(Collectors.toList());
        KundenDTO k = kundenApi.get(Long.valueOf(id));

        model.addAttribute("elements", element == null ? Collections.emptyList() : element);
        model.addAttribute("totalCount", exemplarApi.list(searchRequest).getTotalCount());
        model.addAttribute("kundenDTO", k);

        return (path + "/ausleihelist");
    }

    @Transactional
    @GetMapping("/{id}/{e_id}")
    public String returnMedia(@PathVariable final String id,@PathVariable final String e_id, final Model model) {
        final SearchRequest searchRequest = new SearchRequest(0, 100, null);
        final  List<AusleiheDTO> ausleihen = ausleiheApi.list(new SearchRequest()).getElements().stream().filter(a -> a.getExemplar().getId() == Long.valueOf(e_id)).collect(Collectors.toList());


        ExemplarDTO exemplarDTO = exemplarApi.get(Long.valueOf(e_id));
        exemplarDTO.setAusgeliehen(false);
        exemplarDTO = exemplarApi.update(Long.valueOf(e_id),exemplarDTO);


        final List<KundenDTO> element = kundenApi.list(searchRequest).getElements();
        model.addAttribute("elements", element == null ? Collections.emptyList() : element);
        model.addAttribute("totalCount", kundenApi.list(searchRequest).getTotalCount());
        return (path + "/login");
    }
}
