package com.kardoaward.mobileapp.stage.mapper;

import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.stage.dto.request.CreateStageDtoRequest;
import com.kardoaward.mobileapp.stage.dto.request.UpdateStageDtoRequest;
import com.kardoaward.mobileapp.stage.dto.response.StageDtoResponse;
import com.kardoaward.mobileapp.stage.model.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StageMapper {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    public static List<StageDtoResponse> findAllStageDto(List<Stage> stages) {
        return stages.stream()
                .map(StageMapper::findStageDto)
                .toList();
    }

    public static StageDtoResponse findStageDto(Stage stage) {
        return new StageDtoResponse(
                stage.getId(),
                stage.getEnd().isBefore(LocalDate.now()) ? "Завершон" : "В процессе",
                stage.getName(),
                stage.getDescription(),
                stage.getStart(),
                stage.getEnd(),
                stage.getTask()
        );
    }

    public static Stage mapCreateStage(CreateStageDtoRequest dto, Event event) {
        return Stage.builder()
                .name(dto.getName())
                .description(dto.getDescription())
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
        if (dto.getDescription() != null) {
            stage.setDescription(dto.getDescription());
        }
        if (dto.getTask() != null) {
            stage.setTask(dto.getTask());
        }
        if (dto.getStartDate() != null && !dto.getStartDate().isEmpty()) {
            stage.setStart(LocalDate.parse(dto.getStartDate(), DTF));
        }
        if (dto.getEndDate() != null && !dto.getEndDate().isEmpty()) {
            stage.setEnd(LocalDate.parse(dto.getEndDate(), DTF));
        }
        if (dto.getTask() != null) {
            stage.setTask(dto.getTask());
        }

        return stage;
    }


}
