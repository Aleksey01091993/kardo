package com.kardoaward.mobileapp.request.dto.response;

import com.kardoaward.mobileapp.status.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Сущность для отображения короткого события участника.")
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
