package pl.nutriplan.profileservice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import pl.nutriplan.profileservice.dto.WeightMeasurementDto;
import pl.nutriplan.profileservice.dto.WeightMeasurementRequest;
import pl.nutriplan.profileservice.mapper.WeightMeasurementMapper;
import pl.nutriplan.profileservice.model.UserProfile;
import pl.nutriplan.profileservice.model.WeightMeasurement;
import pl.nutriplan.profileservice.repository.WeightMeasurementRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WeightMeasurementService {


    private final WeightMeasurementRepository weightMeasurementRepository;
    private final WeightMeasurementMapper weightMeasurementMapper;
    private final UserProfileService userProfileService;


    @Transactional
    public WeightMeasurementDto addWeightMeasurement(WeightMeasurementRequest weightMeasurementRequest) {

        UserProfile userProfile = userProfileService.getUserProfile();
        WeightMeasurement weightMeasurement = new WeightMeasurement(
                userProfile,
                weightMeasurementRequest.weight(),
                weightMeasurementRequest.measuredOn()
        );
        WeightMeasurement saveMeasurement = weightMeasurementRepository.save(weightMeasurement);
        return weightMeasurementMapper.toDto(saveMeasurement);
    }

    @Transactional
    public WeightMeasurementDto updateWeightMeasurement(UUID uuid, WeightMeasurementRequest weightMeasurementRequest) {

        UUID userProfileID = userProfileService.getUserProfile().getId();

        WeightMeasurement weightMeasurement = weightMeasurementRepository
                .findByIdAndUserProfile_Id(uuid, userProfileID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found"));
        weightMeasurement.setWeight(weightMeasurementRequest.weight());
        weightMeasurement.setMeasuredDate(weightMeasurementRequest.measuredOn());
        return weightMeasurementMapper.toDto(weightMeasurementRepository.save(weightMeasurement));
    }

    @Transactional
    public List<WeightMeasurementDto> getAllWeightMeasurements() {

        UserProfile userProfile = userProfileService.getUserProfile();
        return weightMeasurementRepository
                .findByUserProfile_IdOrderByMeasuredDateDesc(userProfile.getId())
                .stream()
                .map(weightMeasurementMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteMeasurement(UUID measurement_id) {
        UUID userProfileID = userProfileService.getUserProfile().getId();
        WeightMeasurement weightMeasurement = weightMeasurementRepository
                .findByIdAndUserProfile_Id(measurement_id, userProfileID)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Measurement not found"));
        weightMeasurementRepository.delete(weightMeasurement);
    }

    public List<WeightMeasurementDto> getAllWeightMeasurementsByUserProfile(LocalDate from, LocalDate to) {
        if (from.isAfter(to) || from == null || to == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "From or To are null or from is after to");
        }
        UUID userProfile = userProfileService.getUserProfile().getId();
        return weightMeasurementRepository
                .findByUserProfile_IdAndMeasuredDateBetweenOrderByMeasuredDateDesc(userProfile, from, to)
                .stream()
                .map(weightMeasurementMapper::toDto)
                .toList();
    }
}

