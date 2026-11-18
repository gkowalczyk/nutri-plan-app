package pl.nutriplan.mealdiaryservice.mapper;

import org.springframework.stereotype.Component;
import pl.nutriplan.mealdiaryservice.dto.MealEntryDto;
import pl.nutriplan.mealdiaryservice.model.MealEntry;

@Component
public class MealEntryMapper {

    public MealEntryDto toDto(MealEntry mealEntry) {
        return new MealEntryDto(
                mealEntry.getId(),
                mealEntry.getEatenOn(),
                mealEntry.getFoodProductId(),
                mealEntry.getFoodProductName(),
                mealEntry.getGrams(),
                mealEntry.getCalories(),
                mealEntry.getProteinGrams(),
                mealEntry.getFatGrams(),
                mealEntry.getCarbsGrams()
        );
    }
}
