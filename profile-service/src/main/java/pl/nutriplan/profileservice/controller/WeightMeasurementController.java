package pl.nutriplan.profileservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.nutriplan.profileservice.dto.WeightMeasurementDto;
import pl.nutriplan.profileservice.dto.WeightMeasurementRequest;
import pl.nutriplan.profileservice.service.WeightMeasurementService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/profile/weight-measurements")
@RequiredArgsConstructor
public class WeightMeasurementController {

    private final WeightMeasurementService weightMeasurementService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public WeightMeasurementDto createWeightMeasurement(
            @Valid @RequestBody WeightMeasurementRequest weightMeasurementRequest) {
        return weightMeasurementService.addWeightMeasurement(weightMeasurementRequest);
    }

    @GetMapping
    public List<WeightMeasurementDto> getWeightMeasurements() {
        return weightMeasurementService.getAllWeightMeasurements();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWeightMeasurement(@PathVariable("id") UUID id) {
        weightMeasurementService.deleteMeasurement(id);
    }

    @PutMapping("/{id}")
    public WeightMeasurementDto updateMeasurement(@PathVariable("id") UUID id,
                                                  @Valid @RequestBody
                                                  WeightMeasurementRequest weightMeasurementRequest) {
        return weightMeasurementService.updateWeightMeasurement(id, weightMeasurementRequest);
    }

    @GetMapping("/getAll")
    public List<WeightMeasurementDto> getMeasurements(
            @RequestParam(name = "from", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(name = "to", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to) {
        if (from == null && to == null) {
            return weightMeasurementService.getAllWeightMeasurements();
        }
        return weightMeasurementService.getAllWeightMeasurementsByUserProfile(from, to);
    }
}
