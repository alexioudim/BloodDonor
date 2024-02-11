package dit.hua.team50.BloodDonor.repository;

import dit.hua.team50.BloodDonor.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}
