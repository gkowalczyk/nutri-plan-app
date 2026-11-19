package pl.nutriplan.mealdiaryservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "meal_entries")
@Getter
@NoArgsConstructor
public class MealEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false, length = 255)
    private String userId;

    @Column(name = "eaten_on", nullable = false)
    private LocalDate eatenOn;

    @Column(name = "food_product_id", nullable = false)
    private UUID foodProductId;

    @Column(name = "food_product_name", nullable = false, length = 150)
    private String foodProductName;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal grams;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal calories;

    @Column(name = "protein_grams", nullable = false, precision = 10, scale = 2)
    private BigDecimal proteinGrams;

    @Column(name = "fat_grams", nullable = false, precision = 10, scale = 2)
    private BigDecimal fatGrams;

    @Column(name = "carbs_grams", nullable = false, precision = 10, scale = 2)
    private BigDecimal carbsGrams;

    public MealEntry(
            String userId,
            LocalDate eatenOn,
            UUID foodProductId,
            String foodProductName,
            BigDecimal grams,
            BigDecimal calories,
            BigDecimal proteinGrams,
            BigDecimal fatGrams,
            BigDecimal carbsGrams
    ) {
        this.userId = userId;
        this.eatenOn = eatenOn;
        this.foodProductId = foodProductId;
        this.foodProductName = foodProductName;
        this.grams = grams;
        this.calories = calories;
        this.proteinGrams = proteinGrams;
        this.fatGrams = fatGrams;
        this.carbsGrams = carbsGrams;
    }

    public void updateMealEntry(
            LocalDate eatenOn,
            UUID foodProductId,
            String foodProductName,
            BigDecimal grams,
            BigDecimal calories,
            BigDecimal proteinGrams,
            BigDecimal fatGrams,
            BigDecimal carbsGrams
    ) {
        this.eatenOn = eatenOn;
        this.foodProductId = foodProductId;
        this.foodProductName = foodProductName;
        this.grams = grams;
        this.calories = calories;
        this.proteinGrams = proteinGrams;
        this.fatGrams = fatGrams;
        this.carbsGrams = carbsGrams;
    }
}