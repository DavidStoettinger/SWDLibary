package at.fh.winb.swd.libary.respository;

import at.fh.winb.swd.libary.entity.Bibliothek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliothekRespority extends JpaRepository<Bibliothek,Long> {
}
