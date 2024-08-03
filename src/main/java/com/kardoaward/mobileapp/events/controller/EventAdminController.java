package com.kardoaward.mobileapp.events.controller;

import com.kardoaward.mobileapp.events.dto.request.CreateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.request.UpdateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.response.EventFullDtoResponse;
import com.kardoaward.mobileapp.events.service.EventService;
import jakarta.validation.Valid;
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






}
