package com.kardoaward.mobileapp.stage.service;

import com.kardoaward.mobileapp.stage.dto.request.CreateStageDtoRequest;
import com.kardoaward.mobileapp.stage.dto.request.UpdateStageDtoRequest;
import com.kardoaward.mobileapp.stage.dto.response.StageDtoResponse;

import java.util.List;

public interface StageService {

    void create(Long eventId, CreateStageDtoRequest stage);

    void update(Long eventId, Long stageId, UpdateStageDtoRequest stage);

    void updateById(Long stageId, UpdateStageDtoRequest stage);

    void delete(Long stageId);

    StageDtoResponse findByIdStageDto(Long stageId);

    List<StageDtoResponse> findAllStageDto();


}
