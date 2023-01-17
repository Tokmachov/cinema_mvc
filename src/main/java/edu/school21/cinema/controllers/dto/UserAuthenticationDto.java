package edu.school21.cinema.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAuthenticationDto {
    private String remoteAddress;
    private String date;
    private String time;
}
