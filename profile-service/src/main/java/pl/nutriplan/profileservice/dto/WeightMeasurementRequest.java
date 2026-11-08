package pl.nutriplan.profileservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;

public record WeightMeasurementRequest(

        @NotNull(message = "Masa ciała jest wymagana")
        @DecimalMin(value = "0.01",
                message = "Masa ciała musi być większa od zera")
        @Digits(
                integer = 3,
                fraction = 2,
                message = "Masa ciała może mieć maksymalnie 3 cyfry "
                        + "przed przecinkiem i 2 po przecinku"
        )
        BigDecimal weight,
        @NotNull(message = "Data pomiaru jest wymagana")
        @PastOrPresent(message = "Data pomiaru nie może być z przyszłości")
        LocalDate measuredOn
) {
}
