package com.kardoaward.mobileapp.events.dto.event.response;

import com.kardoaward.mobileapp.events.dto.stage.response.StageEventDtoResponse;

import java.time.LocalDate;
import java.util.List;

public record EventFullDtoResponse(
        Long id,
        String status,
        String name,
        String description,
        LocalDate start,
        LocalDate end,
        String level,
        List<StageEventDtoResponse> stage
) {
}
