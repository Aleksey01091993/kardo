package com.kardoaward.mobileapp.request.dto.response;

import com.kardoaward.mobileapp.status.UserStatus;

import java.time.LocalDate;

public record RequestStageDtoResponse(
        Long id,
        String statusStage,
        String name,
        LocalDate start,
        LocalDate end,
        UserStatus userStatus,
        String result,
        String task
) {
}
