package at.fh.winb.swd.libary.controller;

import at.fh.winb.swd.libary.api.BibliothekApi;
import at.fh.winb.swd.libary.dto.BibliothekDTO;
import at.fh.winb.swd.libary.searchRequest.base.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/bibliothek")
public class BibliothekController {

    private final BibliothekApi bibliothekApi;

    private final String path = "bibliothek";

    @Autowired
    public BibliothekController(final BibliothekApi bibliothekApi) {
        this.bibliothekApi = bibliothekApi;
    }

    @Transactional
    @GetMapping
    public String list(final Model model) {
        final SearchRequest searchRequest = new SearchRequest(0, 100, null);
        final List<BibliothekDTO> element = bibliothekApi.list(searchRequest).getElements();

        model.addAttribute("elements", element == null ? Collections.emptyList() : element);
        model.addAttribute("totalCount", bibliothekApi.list(searchRequest).getTotalCount());

        return (path+"/list-"+path);
    }

    @Transactional
    @GetMapping("/{id}")
    public String get(@PathVariable final String id, final Model model) {
        model.addAttribute("bibliothekDTO", bibliothekApi.get(Long.valueOf(id)));
        return (path+"/show-"+path);
    }

    @GetMapping("/create")
    public String requestCreate(final BibliothekDTO bibliothekDTO) {
        return (path+"/create-"+path);
    }


    @Transactional
    @PostMapping
    public String create(@ModelAttribute final BibliothekDTO bibliothekDTO, final Model model) {
        final BibliothekDTO bibliothek = bibliothekApi.create(bibliothekDTO);
        model.addAttribute("bibliothekDTO", bibliothek);
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
    public String update(@PathVariable final String id, @ModelAttribute final BibliothekDTO bibliothekDTO, final Model model) {
        final BibliothekDTO bibliothek = bibliothekApi.update(Long.valueOf(id), bibliothekDTO);
        model.addAttribute("bibliothekDTO", bibliothek);
        return (path+"/show-"+path);
    }

    @Transactional
    @GetMapping("delete/{id}")
    public String delete(@PathVariable final String id, final Model model) {
        bibliothekApi.delete(Long.valueOf(id));
        return list(model);
    }
}
