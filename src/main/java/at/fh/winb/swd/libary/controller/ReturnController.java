package at.fh.winb.swd.libary.controller;

import at.fh.winb.swd.libary.api.AusleiheApi;
import at.fh.winb.swd.libary.dto.AusleiheDTO;
import at.fh.winb.swd.libary.dto.KundenDTO;
import at.fh.winb.swd.libary.searchRequest.base.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/return")
public class ReturnController {

    final AusleiheApi ausleiheApi;

    private final String path = "return-media";

    @Autowired
    public ReturnController(final AusleiheApi ausleiheApi) {
        this.ausleiheApi = ausleiheApi;
    }


    @Transactional
    @GetMapping()
    public String list(final Model model) {
        final SearchRequest searchRequest = new SearchRequest(0, 100, null);
        final List<AusleiheDTO> element = ausleiheApi.list(searchRequest).getElements();

        model.addAttribute("elements", element == null ? Collections.emptyList() : element);
        model.addAttribute("totalCount", ausleiheApi.list(searchRequest).getTotalCount());

        return (path + "/ausleihelist");
    }
}
