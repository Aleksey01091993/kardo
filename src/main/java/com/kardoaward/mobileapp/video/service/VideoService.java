package com.kardoaward.mobileapp.video.service;

import com.kardoaward.mobileapp.video.model.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoService {

    void uploadVideo(Long userId, MultipartFile file);

    List<Video> getAllVideosByUserId(Long userId);

    Video getVideoById(Long id);
}
