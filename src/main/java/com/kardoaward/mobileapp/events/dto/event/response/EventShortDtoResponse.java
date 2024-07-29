package com.kardoaward.mobileapp.events.dto.event.response;

import java.time.LocalDate;

public record EventShortDtoResponse(
        Long id,
        String status,
        String name,
        LocalDate start,
        LocalDate end,
        String level
) {
}
