package pl.nutriplan.mealdiaryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class MealDiaryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MealDiaryServiceApplication.class, args);


        List<BigDecimal> prices = List.of(
                new BigDecimal("12.50"),
                new BigDecimal("8.20"),
                new BigDecimal("15.30"));

                BigDecimal sum =
                        prices.stream()
                                .map(n -> n.multiply(new BigDecimal(2)))
                                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(sum);



    }

}
