package pl.nutriplan.mealdiaryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.nutriplan.mealdiaryservice.model.MealEntry;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MealEntryRepository extends JpaRepository<MealEntry, UUID> {

    List<MealEntry> findByUserIdAndEatenOnOrderByIdAsc(String userId, LocalDate eatenOrder);
    Optional<MealEntry> findByIdAndUserId(UUID id, String userId);
}
