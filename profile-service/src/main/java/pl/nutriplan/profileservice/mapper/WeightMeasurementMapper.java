package pl.nutriplan.profileservice.mapper;

import org.springframework.stereotype.Component;
import pl.nutriplan.profileservice.dto.UserProfileDto;
import pl.nutriplan.profileservice.dto.WeightMeasurementDto;
import pl.nutriplan.profileservice.model.UserProfile;
import pl.nutriplan.profileservice.model.WeightMeasurement;

@Component
public class WeightMeasurementMapper {


    public WeightMeasurementDto toDto(WeightMeasurement weightMeasurement) {
        return new WeightMeasurementDto(
                weightMeasurement.getId(),
                weightMeasurement.getWeight(),
                weightMeasurement.getMeasuredDate()
        );
    }
}
