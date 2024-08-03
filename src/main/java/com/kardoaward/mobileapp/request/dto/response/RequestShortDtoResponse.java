package com.kardoaward.mobileapp.request.dto.response;

import com.kardoaward.mobileapp.status.UserEventStatus;

import java.time.LocalDate;

public record RequestShortDtoResponse(
        Long id,
        String name,
        LocalDate start,
        LocalDate end,
        UserEventStatus status,
        String stageName,
        String level,
        Integer place
) {
}
