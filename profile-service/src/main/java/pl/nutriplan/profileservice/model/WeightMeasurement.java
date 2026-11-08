package pl.nutriplan.profileservice.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "weight_measurements")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeightMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", nullable = false)
    private UserProfile userProfile;

    @Column(name = "weight_kg", nullable = false, precision = 5, scale = 2)
    private BigDecimal weight;

    @Column(name = "measured_date", nullable = false)
    public LocalDate measuredDate;

    public WeightMeasurement(UserProfile userProfile, BigDecimal weight, LocalDate measuredDate) {
        this.userProfile = userProfile;
        this.weight = weight;
        this.measuredDate = measuredDate;
    }
}
