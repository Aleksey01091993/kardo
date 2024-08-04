package com.kardoaward.mobileapp.exceptions;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {
    private String message;
    private String reason;
    private String status;
    private LocalDateTime timestamp;
}