package pl.nutriplan.profileservice.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nutriplan.profileservice.dto.UserProfileRequest;
import pl.nutriplan.profileservice.model.NutritionObjective;
import pl.nutriplan.profileservice.model.UserProfile;
import pl.nutriplan.profileservice.repository.UserProfileRepository;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Service
@RequiredArgsConstructor
public class UserProfileService {

    private static final String DEMO_USER_PROFILE = "demo-user";
    private final UserProfileRepository userProfileRepository;


    @Transactional
    public UserProfile getUserProfile() {
        return userProfileRepository.findByUserId(DEMO_USER_PROFILE)
                .orElseGet( () -> createDemoProfile());
    }

    public UserProfile createDemoProfile() {
        UserProfile profile = new UserProfile(
                UUID.randomUUID(),
                DEMO_USER_PROFILE,
                "Grzegorz",
                LocalDate.of(1990, 1, 1),
                180,
                NutritionObjective.MAINTAIN_WEIGHT
        );
        return userProfileRepository.save(profile);
    }

    @Transactional
    public UserProfile updateUserProfile(UserProfileRequest userProfileRequest) {
        UserProfile userProfile = userProfileRepository.findByUserId(DEMO_USER_PROFILE)
                .orElseGet(this::createDemoProfile);

        userProfile = new UserProfile(
                userProfile.getId(),
                userProfile.getUserId(),
                userProfileRequest.displayName().trim(),
                userProfileRequest.birthDate(),
                userProfileRequest.heightCm(),
                userProfileRequest.nutritionObjective()
        );

        return userProfileRepository.save(userProfile);
    }
}
