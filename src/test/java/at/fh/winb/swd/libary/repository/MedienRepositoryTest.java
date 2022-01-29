package at.fh.winb.swd.libary.repository;

import at.fh.winb.swd.libary.configuration.IntegrationTestConfiguration;
import at.fh.winb.swd.libary.entity.Medien;
import at.fh.winb.swd.libary.factory.MedienFactory;
import at.fh.winb.swd.libary.respository.MedienRepository;
import at.fh.winb.swd.libary.searchRequest.FSKSearchRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Transactional
@SpringBootTest
@ContextConfiguration(classes = IntegrationTestConfiguration.class)
public class MedienRepositoryTest {
    //create test to find only FSK1 in search
    private final MedienFactory medienFactory;
    private final MedienRepository repository;

    @Autowired
    public MedienRepositoryTest(final MedienFactory medienFactory, final MedienRepository repository){
        this.medienFactory = medienFactory;
        this.repository = repository;
    }

    @Test //create medien with different FSK
    void bySearchRequest(){
        final Medien med1 = medienFactory.testCreateMedien(01L, "testMed1", 18, "testMed18");
        final Medien med2 = medienFactory.testCreateMedien(02L, "testMed2", 1, "testMed1");
        final Medien med3 = medienFactory.testCreateMedien(03L, "testMed3", 1, "testMed1");
        final Medien med4 = medienFactory.testCreateMedien(04L, "testMed4", 18, "testMed18_2");

        //list of medien with fsk 1
        final List<Medien>testsforMed_fsk1 = List.of(med2, med3);
        //list of medien with fsk 18
        final List<Medien>testsforMed_fsk18 = List.of(med1, med4);

        final FSKSearchRequest searchRequest = new FSKSearchRequest(1, 100, Collections.emptyList());
        searchRequest.setMedienID(med1.getId());
        searchRequest.setFSK(med1.getFSK());

        //final List<Medien> results = repository.findAll(MedienRepository.Specs.bySearchRequest)

    }



}
