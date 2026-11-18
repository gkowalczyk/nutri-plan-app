package pl.nutriplan.mealdiaryservice.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record MealEntryRequest(

        @NotNull
        UUID foodProductId,

        @NotNull
        LocalDate eatenOn,

        @NotNull
        @Positive
        @Digits(integer = 6, fraction = 2)
        BigDecimal grams
) {
}
