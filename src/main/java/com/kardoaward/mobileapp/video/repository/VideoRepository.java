package com.kardoaward.mobileapp.video.repository;

import com.kardoaward.mobileapp.video.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findAllByUserId(Long userId);
    Optional<Video> findByUserIdAndId(Long userId, Long id);
}
