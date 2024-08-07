package com.kardoaward.mobileapp.epic.controller;

import com.kardoaward.mobileapp.epic.dto.response.EpicDtoResponse;
import com.kardoaward.mobileapp.epic.service.EpicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Подзадачи публичный.", description = "Возвращает подзадачи.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/public/epic")
public class EpicPublicController {

    private final EpicService epicService;

    @Operation(
            summary = "Вернуть подзадачи.",
            description = "Возвращает подзадачи по ID события."
    )
    @GetMapping("/event/{eventId}")
    public List<EpicDtoResponse> findAllEventId(@PathVariable @Parameter(description = "ID события.") Long eventId) {
        log.info("Пришел GET запрос /public/epic/event/{}", eventId);
        final List<EpicDtoResponse> response = epicService.findAllEventId(eventId);
        log.info("Отправлен ответ для GET запроса /admin/epic/event/{} с телом: {}", eventId, response);
        return response;
    }
    @Operation(
            summary = "Вернуть подзадачу.",
            description = "Возвращает подзадачу по ID."
    )
    @GetMapping("/{epicId}")
    public EpicDtoResponse findById(@PathVariable @Parameter(description = "ID подзадачи.") Long epicId) {
        log.info("Пришел GET запрос /admin/epic/full/{}", epicId);
        final EpicDtoResponse response = epicService.findById(epicId);
        log.info("Отправлен ответ для GET запроса /admin/epic/{} с телом: {}", epicId, response);
        return response;
    }
}
