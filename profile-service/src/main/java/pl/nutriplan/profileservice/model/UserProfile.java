package pl.nutriplan.profileservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user_profiles")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProfile {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, length = 100)
    private String displayName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private Integer heightCm;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NutritionObjective nutritionObjective;

    public UserProfile(String displayName, LocalDate birthDate, Integer heightCm, NutritionObjective nutritionObjective) {
        this.displayName = displayName;
        this.birthDate = birthDate;
        this.heightCm = heightCm;
        this.nutritionObjective = nutritionObjective;
    }

    public UserProfile(UUID id, String userId, String displayName, LocalDate birthDate, Integer heightCm, NutritionObjective nutritionObjective) {
        this.id = id;
        this.userId = userId;
        this.displayName = displayName;
        this.birthDate = birthDate;
        this.heightCm = heightCm;
        this.nutritionObjective = nutritionObjective;
    }
}