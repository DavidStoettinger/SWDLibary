package at.fh.winb.swd.libary.respository;

import at.fh.winb.swd.libary.entity.Kunde;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KundeRepository extends JpaRepository<Kunde,Long> {
}
