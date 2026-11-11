package pl.nutriplan.foodservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "food_products")
@Getter
@NoArgsConstructor
public class FoodProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public FoodProduct(String name, BigDecimal caloriesPer100g,
                       BigDecimal proteinPer100g, BigDecimal fatPer100g,
                       BigDecimal carbsPer100g) {
        this.name = name;
        this.caloriesPer100g = caloriesPer100g;
        this.proteinPer100g = proteinPer100g;
        this.fatPer100g = fatPer100g;
        this.carbsPer100g = carbsPer100g;
    }

    @Column(nullable = false, length = 150)
    private String name;

    @Column(name = "calories_per_100g", nullable = false, precision = 6, scale = 2)
    private BigDecimal caloriesPer100g;

    @Column(name = "protein_per_100g", nullable = false, precision = 5, scale = 2)
    private BigDecimal proteinPer100g;

    @Column(name = "fat_per_100g", nullable = false, precision = 5, scale = 2)
    private BigDecimal fatPer100g;

    @Column(name = "carbs_per_100g", nullable = false, precision = 5, scale = 2)
    private BigDecimal carbsPer100g;
}

