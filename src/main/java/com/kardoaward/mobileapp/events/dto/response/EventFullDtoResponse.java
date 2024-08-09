package com.kardoaward.mobileapp.events.dto.response;

import com.kardoaward.mobileapp.stage.dto.response.StageDtoResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "Сущность со всеми этапами.")
public record EventFullDtoResponse(
        Long id,
        String name,
        String description,
        String category,
        LocalDate start,
        LocalDate end,
        List<StageDtoResponse> stage
) {
}
