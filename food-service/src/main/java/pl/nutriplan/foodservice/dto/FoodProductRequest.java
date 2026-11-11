package pl.nutriplan.foodservice.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record FoodProductRequest(
        @NotBlank @Size(max = 150)
        String name,

        @NotNull @PositiveOrZero @Digits(integer = 4, fraction = 2)
        BigDecimal caloriesPer100g,

        @NotNull @PositiveOrZero @Digits(integer = 3, fraction = 2)
        BigDecimal proteinPer100g,

        @NotNull @PositiveOrZero @Digits(integer = 3, fraction = 2)
        BigDecimal fatPer100g,

        @NotNull @PositiveOrZero @Digits(integer = 3, fraction = 2)
        BigDecimal carbsPer100g
) {
}
