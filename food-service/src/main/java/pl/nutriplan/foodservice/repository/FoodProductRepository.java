package pl.nutriplan.foodservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.nutriplan.foodservice.model.FoodProduct;

import java.util.List;
import java.util.UUID;

@Repository
public interface FoodProductRepository extends JpaRepository<FoodProduct, UUID> {
    List<FoodProduct> findByNameContainingIgnoreCaseOrderByNameAsc (String name);

}
