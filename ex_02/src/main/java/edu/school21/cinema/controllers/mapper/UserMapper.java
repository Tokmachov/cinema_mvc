package edu.school21.cinema.controllers.mapper;

import edu.school21.cinema.controllers.dto.UserPictureDto;
import edu.school21.cinema.controllers.dto.UserProfileDto;
import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserPicture;
import org.apache.commons.lang3.tuple.Pair;
import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserProfileDto toUserProfileDto(User user) {
        String userName = user.getName();
        String currentPictureId = user.getCurrentUserPicture().getId();
        List<UserPictureDto> pictureIdList = user.getUserPictureList()
                .stream()
                .map((userPicture -> new UserPictureDto(userPicture.getId(), userPicture.getOriginalName())))
                .collect(Collectors.toList());
        return new UserProfileDto(userName, currentPictureId, pictureIdList);
    }
}
