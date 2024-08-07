package com.kardoaward.mobileapp.request.dto.response;

import com.kardoaward.mobileapp.status.UserStatus;

import java.util.List;

public record RequestDetailsDtoResponse(
        Long id,
        UserStatus statusUser,
        String category,
        String stageName,
        String level,
        String EventDescription,
        String StageDescription,
        List<RequestStageDtoResponse> stages
) {
}
