package at.fh.winb.swd.libary.respository;

import at.fh.winb.swd.libary.entity.Ausleihe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AusleiheRepository extends JpaRepository<Ausleihe,Long> {
}
