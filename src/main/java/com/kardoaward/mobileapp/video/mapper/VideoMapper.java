package com.kardoaward.mobileapp.video.mapper;

import com.kardoaward.mobileapp.user.mapper.UserMapper;
import com.kardoaward.mobileapp.video.dto.VideoDto;
import com.kardoaward.mobileapp.video.model.Video;
import lombok.experimental.UtilityClass;

public class VideoMapper {

    public static VideoDto toVideoDto(Video video) {
        return VideoDto.builder()
                .id(video.getId())
                .title(video.getTitle())
                .videoPath(video.getVideoPath())
                .user(UserMapper.toUserDto(video.getUser()))
                .build();

    }
}
