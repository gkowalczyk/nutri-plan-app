package pl.nutriplan.profileservice.dto;

import jakarta.validation.constraints.*;
import pl.nutriplan.profileservice.model.ActivityLevel;
import pl.nutriplan.profileservice.model.NutritionObjective;


import java.time.LocalDate;

public record UserProfileRequest(

    @NotBlank(message = "Nazwa nie może być pusta")
    @Size(max = 100, message = "Nazwa może mieć maksymalnie 100 znaków")
    String displayName,

    @NotNull(message = "Data urodzenia jest wymagana")
    @Past(message = "Data urodzenia musi być z przeszłości")
    LocalDate birthDate,


    @NotNull(message = "Wzrost jest wymagany")
    @Min(value = 50, message = "Wzrost musi wynosić co najmniej 50 cm")
    @Max(value = 250, message = "Wzrost może wynosić maksymalnie 250 cm")
    Integer heightCm,

    @NotNull(message = "Cel żywieniowy jest wymagany")
    NutritionObjective nutritionObjective,

    ActivityLevel activityLevel)

{
}
