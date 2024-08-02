package com.kardoaward.mobileapp.events.dto.response;

import com.kardoaward.mobileapp.stage.dto.response.StageEventDtoResponse;

import java.util.List;

public record EventDtoResponse(
        Long id,
        String name,
        String level,
        String description,
        List<StageEventDtoResponse> stage
) {}
