package com.kardoaward.mobileapp.events.dto.response;

import com.kardoaward.mobileapp.stage.dto.response.StageEventDtoResponse;

import java.time.LocalDate;
import java.util.List;

public record EventFullDtoResponse(
        Long id,
        String name,
        String description,
        LocalDate start,
        LocalDate end,
        List<StageEventDtoResponse> stage
) {
}
