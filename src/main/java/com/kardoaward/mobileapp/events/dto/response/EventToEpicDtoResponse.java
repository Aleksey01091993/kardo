package com.kardoaward.mobileapp.events.dto.response;

import com.kardoaward.mobileapp.epic.dto.response.EpicDtoResponse;

import java.util.List;

public record EventToEpicDtoResponse(
        Long id,
        String name,
        String description,
        List<EpicDtoResponse> epics
) {
}
