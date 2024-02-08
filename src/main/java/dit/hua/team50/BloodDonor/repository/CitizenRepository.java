package dit.hua.team50.BloodDonor.repository;

import dit.hua.team50.BloodDonor.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Integer> {

    Optional<Citizen> findByUserId (Long user_id);

    Optional<Citizen> findByEmail (String email);
}
