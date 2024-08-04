package com.kardoaward.mobileapp.user.controller;

import com.kardoaward.mobileapp.user.model.User;
import com.kardoaward.mobileapp.user.service.UserService;
import com.kardoaward.mobileapp.video.model.Video;
import com.kardoaward.mobileapp.video.service.VideoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Пользователи", description = "Взаимодействие с пользователем")
public class UserController {

    private final UserService userService;
    private final VideoService videoService;


    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение пользователя по id")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PatchMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновление данных о пользователе")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @PostMapping("/user/{id}/upload")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Загрузка видео пользователем")
    public void uploadVideo(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        videoService.uploadVideo(id, file);
    }

    @GetMapping("/user/{id}/videos")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение всех видео от пользователя")
    public List<Video> getVideosByUser(@PathVariable Long id) {
        return videoService.getAllVideosByUserId(id);
    }

    @GetMapping("/user/{userId}/videos/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение видео пользователя по id видео")
    public Video getVideoById(@PathVariable Long userId, @PathVariable Long videoId) {
        return videoService.getVideoByIdAndUserId(userId, videoId);
    }
}
