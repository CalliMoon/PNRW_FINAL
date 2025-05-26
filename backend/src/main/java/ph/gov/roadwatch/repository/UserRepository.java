package ph.gov.roadwatch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ph.gov.roadwatch.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);
}