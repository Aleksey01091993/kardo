package com.kardoaward.mobileapp.video.service;

import com.kardoaward.mobileapp.exceptions.FailedToUploadVideoException;
import com.kardoaward.mobileapp.exceptions.NotFoundException;
import com.kardoaward.mobileapp.user.service.UserService;
import com.kardoaward.mobileapp.video.model.Video;
import com.kardoaward.mobileapp.video.repository.VideoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final UserService userService;
    private final List<String> extensions = List.of("avi", "mp4", "mov", "flv", "mkv");

    @Override
    @Transactional
    public void uploadVideo(MultipartFile file, String title) {
        long userId = userService.getUserByAuthentication().getId();
        log.info("Uploading video by user {}", userId);
        byte[] data;
        Path path;
        try {
            if (!extensions.contains(FilenameUtils.getExtension(file.getOriginalFilename()))) {
                throw new FailedToUploadVideoException("Неверный формат файла");
            }
            path = Files.createFile(
                    Path.of("/home/workshop/mobileapp/kardo-mobile-app/videos/" + file.getOriginalFilename()));
            data = file.getBytes();
            Files.write(path, data);
        } catch (IOException e) {
            throw new FailedToUploadVideoException("Не удалось загрузить видео");
        }
        Video video = new Video();
        video.setTitle(title);
        video.setUser(userService.getUser());
        video.setVideoPath(path.toString());
        videoRepository.save(video);
    }

    @Override
    public List<Video> getAllVideosByUserId(Long userId) {
        log.info("Getting all videos by user with id {}", userId);
        return videoRepository.findAllByUserId(userId);
    }

    @Override
    public Video getVideoByIdAndUserId(Long userId, Long id) {
        return videoRepository.findByUserIdAndId(userId, id).
                orElseThrow(() -> new NotFoundException("Видео с таким id не найдено"));
    }
}
