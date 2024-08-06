package com.kardoaward.mobileapp.video.service;

import com.kardoaward.mobileapp.video.model.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface VideoService {

    void uploadVideo(Long userId, MultipartFile file);

    List<Video> getAllVideosByUserId(Long userId);

    Video getVideoByIdAndUserId(Long userId, Long id);
}
