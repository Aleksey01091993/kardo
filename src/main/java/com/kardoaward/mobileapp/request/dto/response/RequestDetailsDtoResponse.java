package com.kardoaward.mobileapp.request.dto.response;

import com.kardoaward.mobileapp.stage.dto.response.StageEventDtoResponse;
import com.kardoaward.mobileapp.status.UserEventStatus;

import java.util.List;

public record RequestDetailsDtoResponse(
        Long id,
        UserEventStatus statusUser,
        String category,
        String stageName,
        String level,
        String description,
        List<StageEventDtoResponse> stages
) {
}
