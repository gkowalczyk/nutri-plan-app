package pl.nutriplan.foodservice.mapper;

import org.springframework.stereotype.Component;
import pl.nutriplan.foodservice.dto.FoodProductDto;
import pl.nutriplan.foodservice.model.FoodProduct;

@Component
public class FoodMapper {

    public FoodProductDto toDto(FoodProduct foodProduct) {
        return new FoodProductDto(
                foodProduct.getId(),
                foodProduct.getName(),
                foodProduct.getCaloriesPer100g(),
                foodProduct.getProteinPer100g(),
                foodProduct.getFatPer100g(),
                foodProduct.getCarbsPer100g()
        );
    }
}
