package at.fh.winb.swd.libary.respository;

import at.fh.winb.swd.libary.entity.Reservierungen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservierungRepository extends JpaRepository<Reservierungen,Long> {
}
