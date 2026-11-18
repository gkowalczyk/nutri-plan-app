package pl.nutriplan.mealdiaryservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DailySummaryDto(
        LocalDate date,
        int entriesCount,
        BigDecimal calories,
        BigDecimal proteinGrams,
        BigDecimal fatGrams,
        BigDecimal carbsGrams
) {
}
