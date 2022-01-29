package at.fh.winb.swd.libary.controller;

import at.fh.winb.swd.libary.api.AusleiheApi;
import at.fh.winb.swd.libary.api.ExemplarApi;
import at.fh.winb.swd.libary.api.KundenApi;
import at.fh.winb.swd.libary.api.ReservierungApi;
import at.fh.winb.swd.libary.dto.ExemplarDTO;
import at.fh.winb.swd.libary.dto.KundenDTO;
import at.fh.winb.swd.libary.dto.ReservierungenDTO;
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
import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("/rent")
public class RentController {

    private final KundenApi kundenApi;
    private final ExemplarApi exemplarApi;
    private final ReservierungApi reservierungApi;
    private final AusleiheApi ausleiheApi;

    private final String path = "rent";

    @Autowired
    public RentController(final KundenApi kundenApi, final ExemplarApi exemplarApi, final ReservierungApi reservierungApi, final AusleiheApi ausleiheApi) {
        this.kundenApi = kundenApi;
        this.exemplarApi = exemplarApi;
        this.reservierungApi = reservierungApi;
        this.ausleiheApi = ausleiheApi;
    }

    @Transactional
    @GetMapping("login")
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
        List<Exemplar> shoppingcart = new LinkedList<>();
        //shoppingcart is going to keep being empty since only instant rent is programmed
        model.addAttribute("kundenDTO", kundenApi.get(Long.valueOf(id)));
        model.addAttribute("shoppingcart", shoppingcart);
        return (path + "/cart-view");
    }

    @Transactional
    @GetMapping("/{id}/exemplarlist")
    public String getList(@PathVariable final String id, final Model model) {
        final SearchRequest searchRequest = new SearchRequest(0, 100, null);
        final List<ExemplarDTO> element = exemplarApi.list(searchRequest).getElements();

        model.addAttribute("kundenDTO", kundenApi.get(Long.valueOf(id)));
        model.addAttribute("exemplar", element == null ? Collections.emptyList() : element);
        return (path + "/exemple-list");
    }

    ///rent/{id}/{e_id}(id=${kundenDTO.id},e_id=${exe.id})
    @Transactional
    @GetMapping("/{id}/{e_id}")
    public String getInstantCheckout(@PathVariable final String id, @PathVariable final String e_id, final Model model) {
        model.addAttribute("kundenDTO", kundenApi.get(Long.valueOf(id)));
        model.addAttribute("exemplarDTO", exemplarApi.get(Long.valueOf(id)));
        return (path + "/instantcheckout");
    }

    @Transactional
    @GetMapping("/{id}/{e_id}/instant")
    public String getInstantCheckoutComplete(@PathVariable final String id, @PathVariable final String e_id, final Model model) {
        ReservierungenDTO reservierungenDTO = new ReservierungenDTO();


        model.addAttribute("kundenDTO", kundenApi.get(Long.valueOf(id)));
        model.addAttribute("exemplarDTO", exemplarApi.get(Long.valueOf(id)));
        return (path + "/instantsuccess");
    }
}
