package at.fh.winb.swd.libary.respository;

import at.fh.winb.swd.libary.entity.Bibliothek;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliothekRepository extends JpaRepository<Bibliothek,Long> {
}
