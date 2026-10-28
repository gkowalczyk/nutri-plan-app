package pl.nutriplan.profileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nutriplan.profileservice.model.UserProfile;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {

   Optional<UserProfile> findByUserId(String uuid);
}
