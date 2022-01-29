package at.fh.winb.swd.libary.factory;

import at.fh.winb.swd.libary.entity.Medien;
import at.fh.winb.swd.libary.respository.MedienRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.function.Consumer;

//factory only to support tests -> create stuff

@TestComponent
public class MedienFactory {
    private final MedienRepository repository;


    @Autowired
    public MedienFactory(final MedienRepository repository){this.repository = repository; }

    @SafeVarargs
    private Medien createBaseEntity(Consumer<Medien>... setters){
        final Medien entity = new Medien();
        entity.setId(new Random().nextLong());
        for(final Consumer<Medien> setter : setters){
            setter.accept(entity);
        }
        return repository != null ? repository.save(entity) : entity;
    }


    public void testCreatingSaveMedia(){
        Medien m = testCreateMedien(20L, "testMedium", 18, "testmediumdescription" );
        testEnterDB(m);
        assert compareTests(m);
    }

    public Medien testCreateMedien(Long id, String name, int fsk, String beschreibung){
        Medien m1 = new Medien();
        m1.setId(id);
        m1.setName(name);
        m1.setFSK(fsk);
        m1.setBeschreibung(beschreibung);
        return m1;
    }

    public void testEnterDB(Medien m1) {
        repository.save(m1);
    }

    public Boolean compareTests(Medien m1){
        return repository.getById(1337L) == m1;
    }
}
