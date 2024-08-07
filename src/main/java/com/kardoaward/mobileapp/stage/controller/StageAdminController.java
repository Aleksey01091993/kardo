package com.kardoaward.mobileapp.stage.controller;

import com.kardoaward.mobileapp.stage.dto.request.CreateStageDtoRequest;
import com.kardoaward.mobileapp.stage.dto.request.UpdateStageDtoRequest;
import com.kardoaward.mobileapp.stage.dto.response.StageDtoResponse;
import com.kardoaward.mobileapp.stage.service.StageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Этапы admin.", description = "Можно добавлять, редактировать удалять и возвращать все этапы.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/stage")
public class StageAdminController {

    private final StageService stageService;

    @Operation(
            summary = "Добавление этапа.",
            description = "Позволяет добавить этап."
    )
    @PostMapping("/events/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid @Parameter(description = "Сущность этапа") CreateStageDtoRequest stageDto,
                       @PathVariable @Valid @Parameter(description = "ID события") Long eventId) {
        log.info("Пришел POST запрос /admin/stage/events/{} с телом: {}", eventId, stageDto);
        stageService.create(eventId, stageDto);
        log.info("Отправлен статус 201 без тела");
    }

    @Operation(
            summary = "Обновление этапа.",
            description = "Позволяет Обновить этап."
    )
    @PatchMapping("/{stageId}/events/{eventId}")
    public void update(@RequestBody @Valid @Parameter(description = "Сущность этапа") UpdateStageDtoRequest stageDto,
                       @PathVariable @Valid @Parameter(description = "ID события") Long eventId,
                       @PathVariable @Valid @Parameter(description = "ID этапа") Long stageId) {
        log.info("Пришел PATH запрос /admin/stage/{}/events/{} с телом: {}", stageId, eventId, stageDto);
        stageService.update(eventId, stageId, stageDto);
        log.info("Отправлен статус 200 без тела");
    }

    @Operation(
            summary = "Обновление этапа.",
            description = "Позволяет Обновить этап."
    )
    @PatchMapping("/{stageId}")
    public void updateById(@RequestBody @Valid @Parameter(description = "Сущность этапа") UpdateStageDtoRequest stageDto,
                           @PathVariable @Valid @Parameter(description = "ID этапа") Long stageId) {
        log.info("Пришел PATH запрос /admin/stage/{} с телом: {}", stageId, stageDto);
        stageService.updateById(stageId, stageDto);
        log.info("Отправлен статус 200 без тела");
    }

    @Operation(
            summary = "Удаление подзадачи.",
            description = "Позволяет удалить подзадачу."
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Valid @Parameter(description = "ID этапа") Long id) {
        log.info("Пришел DELETE запрос /admin/stage/{}", id);
        stageService.delete(id);
        log.info("Отправлен статус 204 без тела");
    }

    @Operation(
            summary = "вернуть этапы",
            description = "Позволяет вернуть все этапы."
    )
    @GetMapping
    public List<StageDtoResponse> findAllDto() {
        log.info("Пришел GET запрос /admin/stage");
        final List<StageDtoResponse> response = stageService.findAllStageDto();
        log.info("Отправлен ответ для GET запроса /admin/events/shorts с телом: {}", response);
        return response;
    }

    @Operation(
            summary = "вернуть этап.",
            description = "Позволяет вернуть этап по id."
    )
    @GetMapping("/{id}")
    public StageDtoResponse findById(@PathVariable @Valid @Parameter(description = "ID этапа") Long id) {
        log.info("Пришел GET запрос /admin/stage/{}", id);
        final StageDtoResponse response = stageService.findByIdStageDto(id);
        log.info("Отправлен ответ для GET запроса /admin/events/shorts/{} с телом: {}", id, response);
        return response;
    }
}
