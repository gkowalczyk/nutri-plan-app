package pl.nutriplan.profileservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.nutriplan.profileservice.dto.UserProfileDto;
import pl.nutriplan.profileservice.model.UserProfile;
import pl.nutriplan.profileservice.service.UserProfileService;

@Component
@RequiredArgsConstructor
public class UserProfileMapper {

    private final UserProfileService userProfileService;


    public UserProfileDto toDto (UserProfile userProfile) {
        return new UserProfileDto(
                userProfile.id(),
                userProfile.displayName(),
                userProfile.birthDate(),
                userProfile.heightCm(),
                userProfile.nutritionObjective()
        );
    }
}
