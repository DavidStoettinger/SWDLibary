package at.fh.winb.swd.libary.controller;

import at.fh.winb.swd.libary.api.KundenApi;
import at.fh.winb.swd.libary.dto.KundenDTO;
import at.fh.winb.swd.libary.searchRequest.base.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/kunden")
public class KundenController {

    private final KundenApi kundenApi;

    private final String path = "kunden";

    @Autowired
    public KundenController(final KundenApi kundenApi) {
        this.kundenApi = kundenApi;
    }

    @Transactional
    @GetMapping
    public String list(final Model model) {
        final SearchRequest searchRequest = new SearchRequest(0, 100, null);
        final List<KundenDTO> element = kundenApi.list(searchRequest).getElements();

        model.addAttribute("elements", element == null ? Collections.emptyList() : element);
        model.addAttribute("totalCount", kundenApi.list(searchRequest).getTotalCount());

        return (path+"/list-"+path);
    }

    @Transactional
    @GetMapping("/{id}")
    public String get(@PathVariable final String id, final Model model) {
        model.addAttribute("kundenDTO", kundenApi.get(Long.valueOf(id)));
        return (path+"/show-"+path);
    }

    @GetMapping("/create")
    public String requestCreate(final KundenDTO kundenDTO) {
        return (path+"/create-"+path);
    }


    @Transactional
    @PostMapping
    public String create(@ModelAttribute final KundenDTO kundenDTO, final Model model) {
        final KundenDTO kunden = kundenApi.create(kundenDTO);
        model.addAttribute("kundenDTO", kunden);
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
    public String update(@PathVariable final String id, @ModelAttribute final KundenDTO kundenDTO, final Model model) {
        final KundenDTO kunden = kundenApi.update(Long.valueOf(id), kundenDTO);
        model.addAttribute("kundenDTO", kunden);
        return (path+"/show-"+path);
    }

    @Transactional
    @GetMapping("delete/{id}")
    public String delete(@PathVariable final String id, final Model model) {
        kundenApi.delete(Long.valueOf(id));
        return list(model);
    }
}
