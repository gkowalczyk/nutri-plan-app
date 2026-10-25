package pl.nutriplan.profileservice;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.nutriplan.profileservice.dto.UserProfileDto;
import pl.nutriplan.profileservice.dto.UserProfileRequest;
import pl.nutriplan.profileservice.mapper.UserProfileMapper;
import pl.nutriplan.profileservice.model.UserProfile;
import pl.nutriplan.profileservice.service.UserProfileService;

@RestController
@RequestMapping("api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserProfileService userProfileService;
    private final UserProfileMapper userProfileMapper;

    @GetMapping
    public UserProfileDto userProfile() {
        UserProfile profile = userProfileService.getUserProfile();
        return userProfileMapper.toDto(profile);
    }

    @PutMapping
    public UserProfileDto userProfile(@Valid @RequestBody
                                   UserProfileRequest userProfileRequest) {
        UserProfile userProfile = userProfileService.updateUserProfile(userProfileRequest);
        return userProfileMapper.toDto(userProfile);
    }
}

