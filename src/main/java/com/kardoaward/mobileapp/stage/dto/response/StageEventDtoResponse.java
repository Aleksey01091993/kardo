package com.kardoaward.mobileapp.stage.dto.response;

import java.time.LocalDate;

public record StageEventDtoResponse(
        Long id,
        String statusStage,
        String name,
        LocalDate start,
        LocalDate end,
        String task
) {
}
