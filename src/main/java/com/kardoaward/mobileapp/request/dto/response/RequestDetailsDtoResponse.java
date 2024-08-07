package com.kardoaward.mobileapp.request.dto.response;

import com.kardoaward.mobileapp.status.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Сущность для отображения события с этапами участника.")
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
