package com.kardoaward.mobileapp.stage.dto.response;

import com.kardoaward.mobileapp.status.StatusStage;

import java.time.LocalDate;

public record StageEventDtoResponse(
        Long id,
        StatusStage statusStage,
        String name,
        LocalDate start,
        LocalDate end,
        String task
) {
}
