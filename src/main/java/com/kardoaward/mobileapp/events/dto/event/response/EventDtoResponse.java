package com.kardoaward.mobileapp.events.dto.event.response;

import com.kardoaward.mobileapp.events.dto.stage.response.StageEventDtoResponse;

import java.util.List;

public record EventDtoResponse(
        Long id,
        String name,
        String level,
        String description,
        List<StageEventDtoResponse> stage
) {}
