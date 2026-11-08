package pl.nutriplan.profileservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nutriplan.profileservice.model.UserProfile;
import pl.nutriplan.profileservice.model.WeightMeasurement;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WeightMeasurementRepository extends JpaRepository<WeightMeasurement, UUID> {

   List<WeightMeasurement> findByUserProfile_IdOrderByMeasuredDateDesc(UUID profile_id);
   Optional<WeightMeasurement> findByIdAndUserProfile_Id(UUID id, UUID profile_id);
   List<WeightMeasurement> findByUserProfile_IdAndMeasuredDateBetweenOrderByMeasuredDateDesc(UUID userProfile_id, LocalDate from ,LocalDate to);
}
