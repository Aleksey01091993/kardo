package com.kardoaward.mobileapp.epic.controller;

import com.kardoaward.mobileapp.epic.dto.request.CreateEpicToEvent;
import com.kardoaward.mobileapp.epic.dto.response.EpicDtoResponse;
import com.kardoaward.mobileapp.epic.service.EpicService;
import com.kardoaward.mobileapp.epic.dto.request.UpdateEpicToEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Подзадачи admin.", description = "Можно добавлять, редактировать, удалять подзадачи и возвращать все подзадачи.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/epic")
public class EpicAdminController {

    private final EpicService epicService;


    @Operation(
            summary = "Добавление подзадачи.",
            description = "Позволяет добавить подзадачу."
    )
    @PostMapping("/event/{eventId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid @Parameter(description = "Сущность подзадачи") CreateEpicToEvent eventDto,
                       @PathVariable @Parameter(description = "ID события") Long eventId) {
        log.info("Пришел POST запрос /admin/epic/event/{} с телом: {}", eventId, eventDto);
        epicService.addToEvent(eventId, eventDto);
        log.info("Отправлен статус 201 без тела");
    }

    @Operation(
            summary = "Обновление подзадачи.",
            description = "Позволяет обновить подзадачу по ID события и ID подзадачи."
    )
    @PatchMapping("/{epicId}/event/{eventId}")
    public void update(@RequestBody @Valid @Parameter(description = "Сущность подзадачи") UpdateEpicToEvent eventDto,
                       @PathVariable @Parameter(description = "ID события.") Long eventId,
                       @PathVariable @Parameter(description = "ID подзадачи.") Long epicId) {
        log.info("Пришел PATH запрос /admin/epic/{}/event/{} с телом: {}", epicId, eventId, eventDto);
        epicService.update(epicId, eventId, eventDto);
        log.info("Отправлен статус 200 без тела");
    }

    @Operation(
            summary = "Обновление подзадачи.",
            description = "Позволяет обновить подзадачу по ID подзадачи."
    )
    @PatchMapping("/{epicId}")
    public void update(@RequestBody @Valid @Parameter(description = "Сущность подзадачи") UpdateEpicToEvent eventDto,
                       @PathVariable @Parameter(description = "ID подзадачи.") Long epicId) {
        log.info("Пришел PATH запрос /admin/epic/{} с телом: {}", epicId, eventDto);
        epicService.update(epicId, eventDto);
        log.info("Отправлен статус 200 без тела");
    }

    @Operation(
            summary = "Удаления подзадачи.",
            description = "Позволяет удалить события."
    )
    @DeleteMapping("/{epicId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Parameter(description = "ID подзадачи.") Long epicId) {
        log.info("Пришел DELETE запрос /admin/epic/{}", epicId);
        epicService.delete(epicId);
        log.info("Отправлен статус 204 без тела");
    }

    @Operation(
            summary = "Удаления подзадачи.",
            description = "Позволяет удалить подзадачу по ID события и ID подзадачи."
    )
    @DeleteMapping("/{epicId}/event/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Parameter(description = "ID события.") Long eventId,
                       @PathVariable @Parameter(description = "ID подзадачи.") Long epicId) {
        log.info("Пришел DELETE запрос /admin/epic/{}/event/{}", epicId, eventId);
        epicService.delete(eventId, epicId);
        log.info("Отправлен статус 204 без тела");
    }

    @Operation(
            summary = "Вернуть подзадачи",
            description = "Возвращает все подзадачи."
    )
    @GetMapping("/all")
    public List<EpicDtoResponse> findAll() {
        log.info("Пришел GET запрос /admin/epic/all");
        final List<EpicDtoResponse> response = epicService.findAll();
        log.info("Отправлен ответ для GET запроса /admin/epic/all с телом: {}", response);
        return response;
    }


}
