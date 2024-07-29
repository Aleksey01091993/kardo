package com.kardoaward.mobileapp.events.service.impl;

import com.kardoaward.mobileapp.events.dto.stage.request.CreateStageDtoRequest;
import com.kardoaward.mobileapp.events.dto.stage.request.UpdateStageDtoRequest;
import com.kardoaward.mobileapp.events.dto.stage.response.StageEventDtoResponse;
import com.kardoaward.mobileapp.events.model.Stage;

import java.util.List;

public interface StageService {


    void create(Long eventId, CreateStageDtoRequest stage);


    void update(Long eventId, UpdateStageDtoRequest stage);


    void delete(Long stageId);


    List<Stage> updateAllStatus(List<Stage> stage);

    StageEventDtoResponse findByIdStageDto(Long stageId);

    List<StageEventDtoResponse> findAllStageDto();


}
