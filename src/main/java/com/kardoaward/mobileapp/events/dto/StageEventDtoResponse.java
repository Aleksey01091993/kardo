package com.kardoaward.mobileapp.events.dto;

import java.time.LocalDate;

public record StageEventDtoResponse(
        Long id,
        String statusStage,
        String name,
        LocalDate start,
        LocalDate end,
        String result,
        String statusUser,
        String level,
        String task
) {
}
