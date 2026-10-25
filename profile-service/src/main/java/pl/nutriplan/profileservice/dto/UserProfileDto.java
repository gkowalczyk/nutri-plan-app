package pl.nutriplan.profileservice.dto;

import pl.nutriplan.profileservice.model.NutritionObjective;

import java.time.LocalDate;
import java.util.UUID;

public record UserProfileDto(
        UUID id,
        String displayName,
        LocalDate birthDate,
        Integer heightCm,
        NutritionObjective nutritionObjective
) {
}
