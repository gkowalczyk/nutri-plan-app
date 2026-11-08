package pl.nutriplan.profileservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record WeightMeasurementDto(
        UUID id,
        BigDecimal weightKg,
        LocalDate measuredOn
) {
}
