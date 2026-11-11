package pl.nutriplan.foodservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.nutriplan.foodservice.dto.FoodPortionDto;
import pl.nutriplan.foodservice.dto.FoodProductDto;
import pl.nutriplan.foodservice.dto.FoodProductRequest;
import pl.nutriplan.foodservice.mapper.FoodMapper;
import pl.nutriplan.foodservice.model.FoodProduct;
import pl.nutriplan.foodservice.repository.FoodProductRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodMapper foodProductMapper;
    private final FoodProductRepository foodProductRepository;

    @Transactional
    public FoodProductDto createProduct(FoodProductRequest foodProductRequest) {

        FoodProduct foodProduct = new FoodProduct(
                foodProductRequest.name().strip(),
                foodProductRequest.caloriesPer100g(),
                foodProductRequest.proteinPer100g(),
                foodProductRequest.fatPer100g(),
                foodProductRequest.carbsPer100g()
        );
        FoodProduct savedFoodProduct = foodProductRepository.save(foodProduct);
        return foodProductMapper.toDto(savedFoodProduct);
    }

    @Transactional
    public List<FoodProductDto> getAllProducts() {
        return foodProductRepository
                .findAll()
                .stream()
                .map(foodProductMapper::toDto)
                .toList();
    }


    public FoodProductDto getProduct(UUID id) {
        return foodProductRepository
                .findById(id)
                .map(foodProductMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Nie znaleziono produktu"
                ));
    }

    public List<FoodProductDto> searchByName(String name) {
        return foodProductRepository
                .findByNameContainingIgnoreCaseOrderByNameAsc(name.strip())
                .stream()
                .map(foodProductMapper::toDto)
                .toList();
    }

    public FoodPortionDto calculatePortion(UUID id, BigDecimal grams) {
        if (grams.signum() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Masa porcji musi być większa od zera");
        }
        FoodProductDto foodProductDto = getProduct(id);
        BigDecimal factor = grams.movePointLeft(2);
        return new FoodPortionDto(
                foodProductDto.id(),
                foodProductDto.name(),
                grams,
                scaleToPortion(foodProductDto.caloriesPer100g(), factor),
                scaleToPortion(foodProductDto.proteinPer100g(), factor),
                scaleToPortion(foodProductDto.fatPer100g(), factor),
                scaleToPortion(foodProductDto.carbsPer100g(), factor)
        );
    }

    public BigDecimal scaleToPortion(BigDecimal valuePer100g, BigDecimal factor) {
        return valuePer100g
                .multiply(factor)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
