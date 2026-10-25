package pl.nutriplan.profileservice.model;

import java.time.LocalDate;
import java.util.UUID;

public record UserProfile(UUID id, String userId, String displayName, LocalDate birthDate, Integer heightCm,
                          NutritionObjective nutritionObjective) {
}
