package com.kardoaward.mobileapp.request.dto.response;

import com.kardoaward.mobileapp.status.UserStatus;

import java.time.LocalDate;
import java.util.List;

public record StageEventDtoResponse(
        Long id,
        String statusStage,
        String name,
        LocalDate start,
        LocalDate end,
        UserStatus userStatus,
        String result
) {
}
