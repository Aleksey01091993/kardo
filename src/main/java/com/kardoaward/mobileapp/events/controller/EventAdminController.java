package com.kardoaward.mobileapp.events.controller;

import com.kardoaward.mobileapp.events.dto.event.request.CreateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.event.request.UpdateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.event.response.EventDtoResponse;
import com.kardoaward.mobileapp.events.dto.event.response.EventFullDtoResponse;
import com.kardoaward.mobileapp.events.dto.event.response.EventShortDtoResponse;
import com.kardoaward.mobileapp.events.service.EventService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/events")
public class EventAdminController {

    private final EventService eventService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CreateEventDtoRequest eventDto) {
        log.info("Пришел POST запрос /admin/events с телом: {}", eventDto);
        eventService.create(eventDto);
        log.info("Отправлен статус 201 без тела");
    }

    @PatchMapping
    public void update(@RequestBody @Valid UpdateEventDtoRequest eventDto) {
        log.info("Пришел PATH запрос /admin/events с телом: {}", eventDto);
        eventService.update(eventDto);
        log.info("Отправлен статус 200 без тела");
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Valid Long id) {
        log.info("Пришел DELETE запрос /admin/events/{}", id);
        eventService.delete(id);
        log.info("Отправлен статус 204 без тела");
    }

    @GetMapping("/shorts")
    public List<EventShortDtoResponse> findAllShortsDto() {
        log.info("Пришел GET запрос /admin/events/shorts");
        final List<EventShortDtoResponse> response = eventService.findAllShortsDto();
        log.info("Отправлен ответ для GET запроса /admin/events/shorts с телом: {}", response);
        return response;
    }

    @GetMapping("/shorts/{id}")
    public EventShortDtoResponse findByEventShortId(@PathVariable Long id) {
        log.info("Пришел GET запрос /admin/events/shorts/{}", id);
        final EventShortDtoResponse response = eventService.findByEventShortId(id);
        log.info("Отправлен ответ для GET запроса /admin/events/shorts/{} с телом: {}", id, response);
        return response;
    }

    @GetMapping("/full")
    public List<EventFullDtoResponse> findAllEventFullDto() {
        log.info("Пришел GET запрос /admin/events/full");
        final List<EventFullDtoResponse> response = eventService.findAllEventFullDto();
        log.info("Отправлен ответ для GET запроса /admin/events/ful с телом: {}", response);
        return response;
    }

    @GetMapping("/full/{id}")
    public EventFullDtoResponse findByIdEventFullDto(@PathVariable Long id) {
        log.info("Пришел GET запрос /admin/events/full/{}", id);
        final EventFullDtoResponse response = eventService.findByIdEventFullDto(id);
        log.info("Отправлен ответ для GET запроса /admin/events/full/{} с телом: {}", id, response);
        return response;
    }

    @GetMapping("/{id}")
    public EventDtoResponse findByEventDtoId(@PathVariable Long id) {
        log.info("Пришел GET запрос /admin/events/{}", id);
        final EventDtoResponse response = eventService.findByEventDtoId(id);
        log.info("Отправлен ответ для GET запроса /admin/events/{} с телом: {}", id, response);
        return response;
    }

    @GetMapping
    public List<EventDtoResponse> findByAllEventDto() {
        log.info("Пришел GET запрос /admin/events");
        final List<EventDtoResponse> response = eventService.findByAllEventDto();
        log.info("Отправлен ответ для GET запроса /admin/events/all с телом: {}", response);
        return response;
    }





}
