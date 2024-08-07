package com.kardoaward.mobileapp.events.dto.response;

import com.kardoaward.mobileapp.stage.dto.response.StageDtoResponse;

import java.time.LocalDate;
import java.util.List;

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
