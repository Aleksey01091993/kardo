package com.kardoaward.mobileapp.video.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kardoaward.mobileapp.user.dto.UserDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDto {

    private long id;
    private String title;
    private String videoPath;
    private UserDto user;
}
