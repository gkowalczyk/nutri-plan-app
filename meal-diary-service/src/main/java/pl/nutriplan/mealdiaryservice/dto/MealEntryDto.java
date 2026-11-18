package pl.nutriplan.mealdiaryservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record MealEntryDto(
        UUID id,
        LocalDate eatenOn,
        UUID foodProductId,
        String foodProductName,
        BigDecimal grams,
        BigDecimal calories,
        BigDecimal proteinGrams,
        BigDecimal fatGrams,
        BigDecimal carbsGrams
) {
}
