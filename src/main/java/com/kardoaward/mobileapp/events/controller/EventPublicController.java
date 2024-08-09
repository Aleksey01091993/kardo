package com.kardoaward.mobileapp.events.controller;


import com.kardoaward.mobileapp.events.dto.response.EventNameDtoResponse;
import com.kardoaward.mobileapp.events.dto.response.EventToEpicDtoResponse;
import com.kardoaward.mobileapp.events.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Публичные события.", description = "Возвращает события для главной страницы, не участвует в личном кабинете.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/public/events")
public class EventPublicController {

    private final EventService eventService;

    @Operation(
            summary = "Вернуть короткое событие.",
            description = "Для главной страницы."
    )
    @GetMapping("/nameAll")
    public List<EventNameDtoResponse> findAllName() {
        log.info("Пришел GET запрос /public/events/nameAll");
        final List<EventNameDtoResponse> response = eventService.findAllName();
        log.info("Отправлен ответ для GET запроса /public/events/nameAll с телом: {}", response);
        return response;
    }

    @Operation(
            summary = "Вернуть событие.",
            description = "Возвращает событие со всеми подзадачами."
    )
    @GetMapping("/all")
    public List<EventToEpicDtoResponse> findAllToEpic() {
        log.info("Пришел GET запрос /public/events/all");
        final List<EventToEpicDtoResponse> response = eventService.findAllToEpic();
        log.info("Отправлен ответ для GET запроса /public/events/all с телом: {}", response);
        return response;
    }

}
