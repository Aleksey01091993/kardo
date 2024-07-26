package com.kardoaward.mobileapp.events.dto;

import java.time.LocalDate;

public record EventShortDtoResponse(
        Long id,
        String name,
        LocalDate start,
        LocalDate end,
        String statusUser,
        String level
) {
}
