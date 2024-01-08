package dit.hua.team50.BloodDonor.repository;

import dit.hua.team50.BloodDonor.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
