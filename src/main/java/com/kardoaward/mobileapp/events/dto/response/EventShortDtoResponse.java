package com.kardoaward.mobileapp.events.dto.response;

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
