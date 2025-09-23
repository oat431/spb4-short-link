package panomete.project.shtlk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import panomete.project.shtlk.entity.ShortLink;

import java.util.UUID;

public interface ShortLinkRepository extends JpaRepository<ShortLink, UUID> {
}
