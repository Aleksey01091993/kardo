package com.kardoaward.mobileapp.events.dto;

import java.util.List;

public record EventDtoResponse(
        Long id,
        String level,
        String description,
        List<StageEventDtoResponse> stage
) {}
