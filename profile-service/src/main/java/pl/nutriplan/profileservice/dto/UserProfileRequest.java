package pl.nutriplan.profileservice.dto;

import jakarta.validation.constraints.*;
import pl.nutriplan.profileservice.model.NutritionObjective;

import java.time.LocalDate;

public record UserProfileRequest(

    @NotBlank
    @Size(max = 100)
    String displayName,

    @NotNull
    @Past
    LocalDate birthDate,

    @NotNull
    @Min(50)
    @Max(250)
    Integer heightCm,

    @NotNull
    NutritionObjective nutritionObjective) {
}
