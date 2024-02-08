package dit.hua.team50.BloodDonor.repository;

import dit.hua.team50.BloodDonor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

}
