package com.kardoaward.mobileapp.video.model;

import com.kardoaward.mobileapp.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Data
@Table(name = "videos")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    @Column(name = "video_path")
    private String videoPath;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
}
