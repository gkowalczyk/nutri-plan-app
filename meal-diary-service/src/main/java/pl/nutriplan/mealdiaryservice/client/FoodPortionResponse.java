package pl.nutriplan.mealdiaryservice.client;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.UUID;

public record FoodPortionResponse(
        @JsonProperty("id")
        UUID productId,

        String name,

        BigDecimal grams,

        @JsonProperty("caloriesPer100g")
        BigDecimal calories,

        @JsonProperty("proteinPer100g")
        BigDecimal proteinGrams,

        @JsonProperty("fatPer100g")
        BigDecimal fatGrams,

        @JsonProperty("carbsPer100g")
        BigDecimal carbsGrams
) {
}