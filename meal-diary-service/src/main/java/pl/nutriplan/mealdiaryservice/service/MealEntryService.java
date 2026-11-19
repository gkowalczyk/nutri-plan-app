package pl.nutriplan.mealdiaryservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.nutriplan.mealdiaryservice.client.FoodPortionResponse;
import pl.nutriplan.mealdiaryservice.client.FoodServiceClient;
import pl.nutriplan.mealdiaryservice.dto.DailySummaryDto;
import pl.nutriplan.mealdiaryservice.dto.MealEntryDto;
import pl.nutriplan.mealdiaryservice.dto.MealEntryRequest;
import pl.nutriplan.mealdiaryservice.mapper.MealEntryMapper;
import pl.nutriplan.mealdiaryservice.model.MealEntry;
import pl.nutriplan.mealdiaryservice.repository.MealEntryRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MealEntryService {

    private final FoodServiceClient foodServiceClient;
    private final MealEntryMapper mealEntryMapper;
    private static final String DEMO_USER_ID = "demo-user";
    private final MealEntryRepository mealEntryRepository;

    @Transactional
    public MealEntryDto createMealEntry(final MealEntryRequest mealEntryRequest) {

        FoodPortionResponse foodPortionResponse = foodServiceClient.calculatePortion(
                mealEntryRequest.foodProductId(),
                mealEntryRequest.grams());

        MealEntry mealEntry = new MealEntry(
                DEMO_USER_ID,
                mealEntryRequest.eatenOn(),
                foodPortionResponse.productId(),
                foodPortionResponse.name(),
                foodPortionResponse.grams(),
                foodPortionResponse.calories(),
                foodPortionResponse.proteinGrams(),
                foodPortionResponse.fatGrams(),
                foodPortionResponse.carbsGrams()
        );

        MealEntry savedMealEntry = mealEntryRepository.save(mealEntry);
        return mealEntryMapper.toDto(savedMealEntry);
    }

    public List<MealEntryDto> getEntries(LocalDate eatenOn) {
        return mealEntryRepository
                .findByUserIdAndEatenOnOrderByIdAsc(DEMO_USER_ID, eatenOn)
                .stream()
                .map(mealEntryMapper::toDto)
                .toList();
    }

    public DailySummaryDto getSummary(LocalDate date) {
        List<MealEntry> mealEntries = mealEntryRepository.findByUserIdAndEatenOnOrderByIdAsc(DEMO_USER_ID, date);

        BigDecimal calories = mealEntries.stream()
                .map(MealEntry::getCalories)
                .reduce(BigDecimal.ZERO, (sum, value) -> sum.add(value));

        BigDecimal protein = mealEntries.stream()
                .map(MealEntry::getProteinGrams)
                .reduce(BigDecimal.ZERO, (sum, value) -> sum.add(value));

        BigDecimal fat = mealEntries.stream()
                .map(MealEntry::getFatGrams)
                .reduce(BigDecimal.ZERO, (sum, value) -> sum.add(value));

        BigDecimal carbs = mealEntries.stream()
                .map(MealEntry::getCarbsGrams)
                .reduce(BigDecimal.ZERO, (sum, value) -> sum.add(value));

        return new DailySummaryDto(
                date,
                mealEntries.size(),
                calories,
                protein,
                fat,
                carbs
        );
    }

    @Transactional
    public void deleteEntry(UUID id) {
        MealEntry mealEntry = mealEntryRepository
                .findByIdAndUserId(id, DEMO_USER_ID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Not found id meal"));
        mealEntryRepository.delete(mealEntry);
    }

    @Transactional
    public MealEntryDto editMealEntry(UUID id, MealEntryRequest request) {

        MealEntry mealEntry = mealEntryRepository.findByIdAndUserId(id, DEMO_USER_ID)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Not found id meal"
                ));

        FoodPortionResponse foodPortionResponseAfterUpdate = foodServiceClient.calculatePortion(
                request.foodProductId(),
                request.grams());

        mealEntry.updateMealEntry(
                request.eatenOn(),
                foodPortionResponseAfterUpdate.productId(),
                foodPortionResponseAfterUpdate.name(),
                foodPortionResponseAfterUpdate.grams(),
                foodPortionResponseAfterUpdate.calories(),
                foodPortionResponseAfterUpdate.proteinGrams(),
                foodPortionResponseAfterUpdate.fatGrams(),
                foodPortionResponseAfterUpdate.carbsGrams()
        );
        return mealEntryMapper.toDto(mealEntry);
    }
}
