package com.kardoaward.mobileapp.events.dto.response;

import com.kardoaward.mobileapp.epic.dto.response.EpicDtoResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Сущность со всеми подзадачами.")
public record EventToEpicDtoResponse(
        Long id,
        String name,
        String description,
        List<EpicDtoResponse> epics
) {
}
