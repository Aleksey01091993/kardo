package com.kardoaward.mobileapp.request.dto.response;

import com.kardoaward.mobileapp.status.UserStatus;

import java.time.LocalDate;

public record RequestStageShortDtoResponse(
        Long id,
        String name,
        LocalDate start,
        LocalDate end,
        UserStatus status,
        String stageName,
        String place,
        String level
) {
}
