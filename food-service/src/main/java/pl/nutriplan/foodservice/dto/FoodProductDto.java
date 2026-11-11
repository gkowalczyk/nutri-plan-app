package pl.nutriplan.foodservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record FoodProductDto(
        UUID id,
        String name,
        BigDecimal caloriesPer100g,
        BigDecimal proteinPer100g,
        BigDecimal fatPer100g,
        BigDecimal carbsPer100g
) {
}
