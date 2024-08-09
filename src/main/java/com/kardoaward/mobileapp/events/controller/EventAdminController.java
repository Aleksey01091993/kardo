package com.kardoaward.mobileapp.events.controller;

import com.kardoaward.mobileapp.events.dto.request.CreateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.request.UpdateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.response.EventFullDtoResponse;
import com.kardoaward.mobileapp.events.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Создание события только для admin", description = "Можно создавать, редактировать, удалять события и получать их полные версии")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/events")
public class EventAdminController {

    private final EventService eventService;

    @Operation(
            summary = "Добавление события.",
            description = "Позволяет добавить события."
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid @Parameter(description = "Сущность пользователя") CreateEventDtoRequest eventDto) {
        log.info("Пришел POST запрос /admin/events с телом: {}", eventDto);
        eventService.create(eventDto);
        log.info("Отправлен статус 201 без тела");
    }

    @Operation(
            summary = "Обновление события.",
            description = "Позволяет обновить события."
    )
    @PatchMapping("{eventId}")
    public void update(@RequestBody @Valid @Parameter(description = "Сущность пользователя") UpdateEventDtoRequest eventDto,
                       @PathVariable @Parameter(description = "ID какого события нужно обновить.") Long eventId) {
        log.info("Пришел PATH запрос /admin/events с телом: {}", eventDto);
        eventService.update(eventDto, eventId);
        log.info("Отправлен статус 200 без тела");
    }

    @Operation(
            summary = "Удаления события.",
            description = "Позволяет удалить события."
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Parameter(description = "ID какого события нужно удалить.") Long id) {
        log.info("Пришел DELETE запрос /admin/events/{}", id);
        eventService.delete(id);
        log.info("Отправлен статус 204 без тела");
    }

    @Operation(
            summary = "Вернуть все события.",
            description = "Возвращает все события с их подзадачами и детальным описанием."
    )
    @GetMapping("/full")
    public List<EventFullDtoResponse> findAllEventFullDto() {
        log.info("Пришел GET запрос /admin/events/full");
        final List<EventFullDtoResponse> response = eventService.findAllEventFullDto();
        log.info("Отправлен ответ для GET запроса /admin/events/ful с телом: {}", response);
        return response;
    }

    @Operation(
            summary = "Вернуть события",
            description = "Возвращает события по ID с его подзадачами и детальным описанием."
    )
    @GetMapping("/full/{id}")
    public EventFullDtoResponse findByIdEventFullDto(@PathVariable @Parameter(description = "ID какого события нужно вернуть.") Long id) {
        log.info("Пришел GET запрос /admin/events/full/{}", id);
        final EventFullDtoResponse response = eventService.findByIdEventFullDto(id);
        log.info("Отправлен ответ для GET запроса /admin/events/full/{} с телом: {}", id, response);
        return response;
    }






}
