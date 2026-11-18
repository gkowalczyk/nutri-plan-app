package pl.nutriplan.mealdiaryservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.nutriplan.mealdiaryservice.dto.DailySummaryDto;
import pl.nutriplan.mealdiaryservice.dto.MealEntryDto;
import pl.nutriplan.mealdiaryservice.dto.MealEntryRequest;
import pl.nutriplan.mealdiaryservice.repository.MealEntryRepository;
import pl.nutriplan.mealdiaryservice.service.MealEntryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealEntryController {

    private final MealEntryService mealEntryService;
    private final MealEntryRepository mealEntryRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MealEntryDto addMealEntry(@RequestBody @Valid MealEntryRequest request) {
        return mealEntryService.createMealEntry(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MealEntryDto> getMealEntries(
            @RequestParam(name = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        return mealEntryService.getEntries(date);
    }

    @GetMapping("/summary")
    public DailySummaryDto getSummary(
            @RequestParam(name = "date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ) {
        return mealEntryService.getSummary(date);
    }
}
