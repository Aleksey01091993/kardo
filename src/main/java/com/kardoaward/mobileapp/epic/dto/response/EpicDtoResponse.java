package com.kardoaward.mobileapp.epic.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Сущность подзадачи для ответа.")
public record EpicDtoResponse(
        Long id,
        String name,
        String description
) {
}
