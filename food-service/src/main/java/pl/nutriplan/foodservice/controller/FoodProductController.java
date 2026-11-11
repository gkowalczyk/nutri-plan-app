package pl.nutriplan.foodservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.nutriplan.foodservice.dto.FoodPortionDto;
import pl.nutriplan.foodservice.dto.FoodProductDto;
import pl.nutriplan.foodservice.dto.FoodProductRequest;
import pl.nutriplan.foodservice.service.FoodService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/foods")
@RequiredArgsConstructor
public class FoodProductController {

    private final FoodService foodService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FoodProductDto createFoodProduct(@Valid @RequestBody FoodProductRequest foodProductRequest) {
        return foodService.createProduct(foodProductRequest);
    }

    @GetMapping
    public List<FoodProductDto> getAllFoodProducts() {
        return foodService.getAllProducts();
    }

    @GetMapping("/{id}")
    public FoodProductDto getProductById(@PathVariable("id") UUID id) {
        return foodService.getProduct(id);
    }

    @GetMapping("/byName")
    public List<FoodProductDto> getAllFoodProductsByName(@RequestParam(name = "name", required = false) String name) {
        if  (name == null || name.isBlank()) {
            return foodService.getAllProducts();
        }
        return foodService.searchByName(name);
    }

    @GetMapping("/{id}/portion")
    public FoodPortionDto calculatePortion(
            @PathVariable("id") UUID id,
            @RequestParam("grams") BigDecimal grams) {
        return foodService.calculatePortion(id, grams);
    }
}

