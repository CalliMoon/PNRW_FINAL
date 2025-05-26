package ph.gov.roadwatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ph.gov.roadwatch.model.Road;

public interface RoadRepository extends JpaRepository<Road, Long> {
}