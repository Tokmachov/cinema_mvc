package edu.school21.cinema.controllers.mapper;

import edu.school21.cinema.controllers.AddressUtils;
import edu.school21.cinema.controllers.dto.UserAuthenticationDto;
import edu.school21.cinema.controllers.utils.TimeUtils;
import edu.school21.cinema.models.UserAuthentication;
import org.apache.commons.lang3.tuple.Pair;

public class UserAuthenticationMapper {
    public static UserAuthenticationDto toDto(UserAuthentication userAuthentication) {
        Pair<String, String> dateAndTime = TimeUtils.toDateAndTime(userAuthentication.getTimeStamp());
        return new UserAuthenticationDto(AddressUtils.toIpV4(userAuthentication.getRemoteAddress()), dateAndTime.getLeft(), dateAndTime.getRight());
    }
}
