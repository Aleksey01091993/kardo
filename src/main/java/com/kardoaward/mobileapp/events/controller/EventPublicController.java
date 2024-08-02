package com.kardoaward.mobileapp.events.controller;


import com.kardoaward.mobileapp.events.dto.response.EventFullDtoResponse;
import com.kardoaward.mobileapp.events.dto.response.EventNameDtoResponse;
import com.kardoaward.mobileapp.events.dto.response.EventShortDtoResponse;
import com.kardoaward.mobileapp.events.dto.response.EventToEpicDtoResponse;
import com.kardoaward.mobileapp.events.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/public/events")
public class EventPublicController {

    private final EventService eventService;

    @GetMapping("/nameAll")
    public List<EventNameDtoResponse> findAllName() {
        log.info("Пришел GET запрос /public/events/nameAll");
        final List<EventNameDtoResponse> response = eventService.findAllName();
        log.info("Отправлен ответ для GET запроса /public/events/nameAll с телом: {}", response);
        return response;
    }

    @GetMapping("/all")
    public List<EventToEpicDtoResponse> findAllToEpic() {
        log.info("Пришел GET запрос /public/events/all");
        final List<EventToEpicDtoResponse> response = eventService.findAllToEpic();
        log.info("Отправлен ответ для GET запроса /public/events/all с телом: {}", response);
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


}
