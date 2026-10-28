package pl.nutriplan.profileservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.nutriplan.profileservice.dto.UserProfileDto;
import pl.nutriplan.profileservice.model.UserProfile;
import pl.nutriplan.profileservice.service.UserProfileService;

@Component
public class UserProfileMapper {


    public UserProfileDto toDto (UserProfile userProfile) {
        return new UserProfileDto(
                userProfile.getId(),
                userProfile.getDisplayName(),
                userProfile.getBirthDate(),
                userProfile.getHeightCm(),
                userProfile.getNutritionObjective()
        );
    }
}
