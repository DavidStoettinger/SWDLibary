package at.fh.winb.swd.libary.controller;

import at.fh.winb.swd.libary.configuration.IntegrationTestConfiguration;
import at.fh.winb.swd.libary.dto.MedienDTO;
import at.fh.winb.swd.libary.entity.Medien;
import at.fh.winb.swd.libary.factory.MedienFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
 class ControllerTest {
    private final MockMvc mockMvc;
    private final MedienFactory medienFactory;

    @Autowired
    public ControllerTest(final MockMvc mockMvc, final MedienFactory medienFactory) {
        this.mockMvc = mockMvc;
        this.medienFactory = medienFactory;
    }

    @Test
    void createNewMedium() throws Exception {
        final Medien m = MedienFactory.testCreateMedien(21L, "tMed", 18, "tMed1");

        //check no media exist
        mockMvc.perform(MockMvcRequestBuilders.get("/medien/" + m.getId()).contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("Id"))
                .andExpect(model().attributeExists("FSK"))
                .andExpect(model().attributeExists("Name"))
                .andExpect(model().attributeExists("Beschreibung"))
                .andExpect(view().name("medien/list-medien"));

        //request to create new media
        mockMvc.perform(MockMvcRequestBuilders.get("/medien/create" + m.getId())
                        .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("id"))
                .andExpect(view().name("medien/list-medien"));

        //create new media and check if only one media exists
        final MedienDTO medienDTO = new MedienDTO();
        medienDTO.setBeschreibung("testbeschreibung");
        medienDTO.setFSK(18);
        medienDTO.setName("test2");
        medienDTO.setId(123L);
        mockMvc.perform(MockMvcRequestBuilders.post("/medien" + medienDTO.getId())
                .flashAttr("medienDTO", medienDTO)
                .contentType(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("id"))
                .andExpect(model().attributeExists("FSK"))
                .andExpect(model().attributeExists("Name"))
                .andExpect(model().attributeExists("Beschreibung"))
                .andExpect(view().name("medien/list-medien"));

    }
}
