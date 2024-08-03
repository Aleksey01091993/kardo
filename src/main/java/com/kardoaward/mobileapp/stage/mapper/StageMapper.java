package com.kardoaward.mobileapp.stage.mapper;

import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.stage.dto.response.StageEventDtoResponse;
import com.kardoaward.mobileapp.stage.dto.request.CreateStageDtoRequest;
import com.kardoaward.mobileapp.stage.dto.request.UpdateStageDtoRequest;
import com.kardoaward.mobileapp.stage.model.Stage;
import com.kardoaward.mobileapp.status.StatusStage;

import java.time.LocalDate;
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
                stage.getStart().isAfter(LocalDate.now()) ? StatusStage.COMPLETED : StatusStage.IN_PROGRESS,
                stage.getName(),
                stage.getStart(),
                stage.getEnd(),
                stage.getTask()
        );
    }

    public static Stage mapCreateStage(CreateStageDtoRequest dto, Event event) {
        return Stage.builder()
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
