package pl.nutriplan.foodservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record FoodPortionDto(
        UUID id,
        String name,
        BigDecimal grams,
        BigDecimal caloriesPer100g,
        BigDecimal proteinPer100g,
        BigDecimal fatPer100g,
        BigDecimal carbsPer100g
) {
}
