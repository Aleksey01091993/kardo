package com.kardoaward.mobileapp.request.service;

import com.kardoaward.mobileapp.request.dto.request.StatusAdminToRequest;
import com.kardoaward.mobileapp.request.dto.request.StatusUserToRequest;
import com.kardoaward.mobileapp.request.dto.request.UpdateRequestStage;
import com.kardoaward.mobileapp.request.dto.response.RequestDetailsDtoResponse;
import com.kardoaward.mobileapp.request.dto.response.RequestStageShortDtoResponse;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestService {
    @Transactional
    void addRequest(Long userId, Long eventId, StatusUserToRequest dto);

    @Transactional
    void updateStatus(Long requestId, StatusAdminToRequest dto);

    @Transactional
    void updateRequestAndStage(Long stageId, Long requestId, UpdateRequestStage dto);

    List<RequestStageShortDtoResponse> findAllShortsActive(Long requesterId);

    List<RequestStageShortDtoResponse> findAllShortsNotActive(Long requesterId);

    RequestDetailsDtoResponse findByDetails(Long requestId);
}
