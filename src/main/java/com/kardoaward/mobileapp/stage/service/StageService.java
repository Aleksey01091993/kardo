package com.kardoaward.mobileapp.stage.service;

import com.kardoaward.mobileapp.stage.dto.request.CreateStageDtoRequest;
import com.kardoaward.mobileapp.stage.dto.request.UpdateStageDtoRequest;
import com.kardoaward.mobileapp.request.dto.response.StageEventDtoResponse;

import java.util.List;

public interface StageService {

    void create(Long eventId, CreateStageDtoRequest stage);

    void update(Long eventId, Long stageId, UpdateStageDtoRequest stage);

    void updateById(Long stageId, UpdateStageDtoRequest stage);

    void delete(Long stageId);

    StageEventDtoResponse findByIdStageDto(Long stageId);

    List<StageEventDtoResponse> findAllStageDto();


}