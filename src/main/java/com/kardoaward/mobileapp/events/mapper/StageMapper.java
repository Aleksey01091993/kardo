package com.kardoaward.mobileapp.events.mapper;

import com.kardoaward.mobileapp.events.dto.stage.request.CreateStageDtoRequest;
import com.kardoaward.mobileapp.events.dto.stage.request.UpdateStageDtoRequest;
import com.kardoaward.mobileapp.events.dto.stage.response.StageEventDtoResponse;
import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.events.model.Stage;

import java.util.List;

public class StageMapper {

    public static List<StageEventDtoResponse> findAllStageDto(List<Stage> stages) {
        return stages.stream()
                .map(StageMapper::findStageDto)
                .toList();
    }

    public static StageEventDtoResponse findStageDto(Stage stage) {
        return new StageEventDtoResponse(
                stage.getId(),
                stage.getStageStatus(),
                stage.getName(),
                stage.getStart(),
                stage.getEnd(),
                stage.getTask()
        );
    }

    public static Stage mapCreateStage(CreateStageDtoRequest dto, Event event) {
        return Stage.builder()
                .stageStatus("waiting")
                .name(dto.getName())
                .start(dto.getStartDate())
                .end(dto.getEndDate())
                .task(dto.getTask())
                .event(event)
                .build();
    }

    public static Stage mapUpdateStage(UpdateStageDtoRequest dto, Stage stage) {
        if (dto.getName() != null) {
            stage.setName(dto.getName());
        }
        if (dto.getTask() != null) {
            stage.setTask(dto.getTask());
        }
        if (dto.getStartDate() != null) {
            stage.setStart(dto.getStartDate());
        }
        if (dto.getEndDate() != null) {
            stage.setEnd(dto.getEndDate());
        }
        if (dto.getTask() != null) {
            stage.setTask(dto.getTask());
        }

        return stage;
    }


}
