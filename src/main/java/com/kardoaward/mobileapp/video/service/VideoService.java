package com.kardoaward.mobileapp.video.service;

import com.kardoaward.mobileapp.video.model.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface VideoService {

    void uploadVideo(MultipartFile file, String title);

    List<Video> getAllVideosByUserId(Long userId);

    Video getVideoByIdAndUserId(Long userId, Long id);
}
