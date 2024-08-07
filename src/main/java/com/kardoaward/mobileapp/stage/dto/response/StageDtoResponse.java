package com.kardoaward.mobileapp.stage.dto.response;

import java.time.LocalDate;

public record StageDtoResponse(
        Long id,
        String statusStage,
        String name,
        LocalDate start,
        LocalDate end,
        String task
) {
}
