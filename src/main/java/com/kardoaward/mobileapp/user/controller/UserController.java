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


    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение пользователя по id")
    public User getUser() {
        return userService.getUser();
    }

    @PatchMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Обновление данных о пользователе")
    public User updateUser(@RequestBody User user) {
        return userService.update(user);
    }

    @PostMapping("/user/upload")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Загрузка видео пользователем")
    public void uploadVideo(@RequestParam("file") MultipartFile file,
                            @RequestParam("title") String title) {
        videoService.uploadVideo(file, title);
    }

    @GetMapping("/user/videos")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение всех видео от пользователя")
    public List<Video> getVideosByUser() {
        return videoService.getAllVideosByUserId();
    }

    @GetMapping("/user/videos/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Получение видео пользователя по id видео")
    public Video getVideoById(@PathVariable Long videoId) {
        return videoService.getVideoByIdAndUserId(videoId);
    }
}
