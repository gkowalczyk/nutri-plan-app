package pl.nutriplan.profileservice.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import pl.nutriplan.profileservice.dto.UserProfileRequest;
import pl.nutriplan.profileservice.model.NutritionObjective;
import pl.nutriplan.profileservice.model.UserProfile;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Service
public class UserProfileService {

    public UserProfile userProfile =  new UserProfile(
            UUID.randomUUID(),
            "demo-user",
            "Grzegorz",
            LocalDate.of(1990, 1, 1),
            180,
            NutritionObjective.MAINTAIN_WEIGHT
    );

    public synchronized UserProfile updateUserProfile(UserProfileRequest userProfileRequest) {

        userProfile = new UserProfile(
                userProfile.id(),
                userProfile.userId(),
                userProfileRequest.displayName().trim(),
                userProfileRequest.birthDate(),
                userProfileRequest.heightCm(),
                userProfileRequest.nutritionObjective()
        );

        return userProfile;
    }
}
