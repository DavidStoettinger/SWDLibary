package at.fh.winb.swd.libary.controller;


import at.fh.winb.swd.libary.api.ReservierungApi;
import at.fh.winb.swd.libary.dto.ReservierungenDTO;
import at.fh.winb.swd.libary.searchRequest.base.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/reservierungen")
public class ReservierungenController {

    private final ReservierungApi reservierungenApi;

    private final String path = "reservierungen";

    @Autowired
    public ReservierungenController(final ReservierungApi reservierungenApi) {
        this.reservierungenApi = reservierungenApi;
    }

    @Transactional
    @GetMapping
    public String list(final Model model) {
        final SearchRequest searchRequest = new SearchRequest(0, 100, null);
        final List<ReservierungenDTO> element = reservierungenApi.list(searchRequest).getElements();

        model.addAttribute("elements", element == null ? Collections.emptyList() : element);
        model.addAttribute("totalCount", reservierungenApi.list(searchRequest).getTotalCount());

        return (path+"/list-"+path);
    }

    @Transactional
    @GetMapping("/{id}")
    public String get(@PathVariable final String id, final Model model) {
        model.addAttribute("reservierungenDTO", reservierungenApi.get(Long.valueOf(id)));
        return (path+"/show-"+path);
    }

    @GetMapping("/create")
    public String requestCreate(final ReservierungenDTO reservierungenDTO) {
        return (path+"/create-"+path);
    }


    @Transactional
    @PostMapping
    public String create(@ModelAttribute final ReservierungenDTO reservierungenDTO, final Model model) {
        final ReservierungenDTO reservierungen = reservierungenApi.create(reservierungenDTO);
        model.addAttribute("reservierungenDTO", reservierungen);
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
    public String update(@PathVariable final String id, @ModelAttribute final ReservierungenDTO reservierungenDTO, final Model model) {
        final ReservierungenDTO reservierungen = reservierungenApi.update(Long.valueOf(id), reservierungenDTO);
        model.addAttribute("reservierungenDTO", reservierungen);
        return (path+"/show-"+path);
    }

    @Transactional
    @GetMapping("delete/{id}")
    public String delete(@PathVariable final String id, final Model model) {
        reservierungenApi.delete(Long.valueOf(id));
        return list(model);
    }
}