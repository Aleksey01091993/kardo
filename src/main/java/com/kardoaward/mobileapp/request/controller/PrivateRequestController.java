package com.kardoaward.mobileapp.request.controller;


import com.kardoaward.mobileapp.request.dto.response.RequestDetailsDtoResponse;
import com.kardoaward.mobileapp.request.dto.response.RequestStageShortDtoResponse;
import com.kardoaward.mobileapp.request.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "заявки этапы", description = "Можно подать заявку и отслеживать результаты события с этапами.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/requests")
public class PrivateRequestController {


    private final RequestService requestService;

    @Operation(
            summary = "Подать заявку на участие.",
            description = "становится участником события и всех этапов"
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/event/{eventId}")
    public void addRequest(@PathVariable @Parameter(description = "ID события.") Long eventId) {
        log.info("Пришел PATCH запрос requests/event/{}", eventId);
        requestService.addRequest(eventId);
        log.info("Отправлен статус 201 без тела");
    }

    @Operation(
            summary = "Активные события пользователя",
            description = "Возвращает все активные события."
    )
    @GetMapping("/active")
    public List<RequestStageShortDtoResponse> findAllShortsActive() {
        log.info("Пришел GET запрос requests/active");
        final List<RequestStageShortDtoResponse> response = requestService.findAllShortsActive();
        log.info("Отправлен ответ для GET запроса requests/active с телом: {}", response);
        return response;
    }

    @Operation(
            summary = "Завершённые события пользователя",
            description = "Возвращает все завершённые события."
    )
    @GetMapping("/notActive")
    public List<RequestStageShortDtoResponse> findAllShortsNotActive() {
        log.info("Пришел GET запрос requests/notActive");
        final List<RequestStageShortDtoResponse> response = requestService.findAllShortsNotActive();
        log.info("Отправлен ответ для GET запроса requests/notActive с телом: {}", response);
        return response;
    }

    @Operation(
            summary = "События пользователя с этапами.",
            description = "Подробное описание события пользователя с этапами."
    )
    @GetMapping("/details")
    public RequestDetailsDtoResponse findByDetails() {
        log.info("Пришел GET запрос requests/details");
        final RequestDetailsDtoResponse response = requestService.findByDetails();
        log.info("Отправлен ответ для GET запроса requests/details с телом: {}", response);
        return response;
    }

}
