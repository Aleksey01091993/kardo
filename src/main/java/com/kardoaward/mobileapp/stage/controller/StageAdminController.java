package com.kardoaward.mobileapp.stage.controller;

import com.kardoaward.mobileapp.stage.dto.request.CreateStageDtoRequest;
import com.kardoaward.mobileapp.stage.dto.request.UpdateStageDtoRequest;
import com.kardoaward.mobileapp.stage.dto.response.StageDtoResponse;
import com.kardoaward.mobileapp.stage.service.StageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/stage")
public class StageAdminController {

    private final StageService stageService;

    @PostMapping("/events/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CreateStageDtoRequest stageDto,
                       @PathVariable @Valid Long eventId) {
        log.info("Пришел POST запрос /admin/stage/events/{} с телом: {}", eventId, stageDto);
        stageService.create(eventId, stageDto);
        log.info("Отправлен статус 201 без тела");
    }

    @PatchMapping("/{stageId}/events/{eventId}")
    public void update(@RequestBody @Valid UpdateStageDtoRequest stageDto,
                       @PathVariable @Valid Long eventId,
                       @PathVariable @Valid Long stageId) {
        log.info("Пришел PATH запрос /admin/stage/{}/events/{} с телом: {}", stageId, eventId, stageDto);
        stageService.update(eventId, stageId, stageDto);
        log.info("Отправлен статус 200 без тела");
    }

    @PatchMapping("/{stageId}")
    public void updateById(@RequestBody @Valid UpdateStageDtoRequest stageDto,
                           @PathVariable @Valid Long stageId) {
        log.info("Пришел PATH запрос /admin/stage/{} с телом: {}", stageId, stageDto);
        stageService.updateById(stageId, stageDto);
        log.info("Отправлен статус 200 без тела");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Valid Long id) {
        log.info("Пришел DELETE запрос /admin/stage/{}", id);
        stageService.delete(id);
        log.info("Отправлен статус 204 без тела");
    }

    @GetMapping
    public List<StageDtoResponse> findAllDto() {
        log.info("Пришел GET запрос /admin/stage");
        final List<StageDtoResponse> response = stageService.findAllStageDto();
        log.info("Отправлен ответ для GET запроса /admin/events/shorts с телом: {}", response);
        return response;
    }

    @GetMapping("/{id}")
    public StageDtoResponse findById(@PathVariable @Valid Long id) {
        log.info("Пришел GET запрос /admin/stage/{}", id);
        final StageDtoResponse response = stageService.findByIdStageDto(id);
        log.info("Отправлен ответ для GET запроса /admin/events/shorts/{} с телом: {}", id, response);
        return response;
    }
}
