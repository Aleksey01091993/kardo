package com.kardoaward.mobileapp.request.service;

import com.kardoaward.mobileapp.request.dto.request.UpdateRequestStage;
import com.kardoaward.mobileapp.request.dto.response.RequestDetailsDtoResponse;
import com.kardoaward.mobileapp.request.dto.response.RequestStageShortDtoResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestService {

    void addRequest(final Long eventId);

    void updateRequestAndStage(Long stageId, Long requestId, UpdateRequestStage dto);

    List<RequestStageShortDtoResponse> findAllShortsActive();

    List<RequestStageShortDtoResponse> findAllShortsNotActive();

    RequestDetailsDtoResponse findByDetails();
}
