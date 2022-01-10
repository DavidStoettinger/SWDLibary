package at.fh.winb.swd.libary.controller;

import at.fh.winb.swd.libary.api.KundenApi;
import at.fh.winb.swd.libary.api.MedienApi;
import at.fh.winb.swd.libary.dto.KundenDTO;
import at.fh.winb.swd.libary.dto.MedienDTO;
import at.fh.winb.swd.libary.searchRequest.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/medien")
public class MedienController {

    private final MedienApi medienApi;

    private final String path = "medien";

    @Autowired
    public MedienController(final MedienApi medienApi) {
        this.medienApi = medienApi;
    }

    @Transactional
    @GetMapping
    public String list(final Model model) {
        final SearchRequest searchRequest = new SearchRequest(0, 100, null);
        final List<MedienDTO> element = medienApi.list(searchRequest).getElements();

        model.addAttribute("elements", element == null ? Collections.emptyList() : element);
        model.addAttribute("totalCount", medienApi.list(searchRequest).getTotalCount());

        return (path+"/list-"+path);
    }

    @Transactional
    @GetMapping("/{id}")
    public String get(@PathVariable final String id, final Model model) {
        model.addAttribute("medienDTO", medienApi.get(Long.valueOf(id)));
        return (path+"/show-"+path);
    }

    @GetMapping("/create")
    public String requestCreate(final MedienDTO medienDTO) {
        return (path+"/create-"+path);
    }


    @Transactional
    @PostMapping
    public String create(@ModelAttribute final MedienDTO medienDTO, final Model model) {
        final MedienDTO medien = medienApi.create(medienDTO);
        model.addAttribute("medienDTO", medien);
        return (path+"/show-"+path);
    }

    @Transactional
    @GetMapping("/edit/{id}")
    public String requestEdit(@PathVariable final String id, final Model model) {
        this.get(id, model);
        return (path+"/update-"+path);
    }

    @Transactional
    @PostMapping("/update/{id}")
    public String update(@PathVariable final String id, @ModelAttribute final MedienDTO medienDTO, final Model model) {
        final MedienDTO medien = medienApi.update(Long.valueOf(id), medienDTO);
        model.addAttribute("medienDTO", medien);
        return (path+"/show-"+path);
    }

    @Transactional
    @GetMapping("delete/{id}")
    public String delete(@PathVariable final String id, final Model model) {
        medienApi.delete(Long.valueOf(id));
        return list(model);
    }
}
