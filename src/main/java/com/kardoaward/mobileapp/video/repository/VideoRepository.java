package com.kardoaward.mobileapp.video.repository;

import com.kardoaward.mobileapp.video.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByUserId(Long userId);
    Video findByVideoId(Long videoId);
}
