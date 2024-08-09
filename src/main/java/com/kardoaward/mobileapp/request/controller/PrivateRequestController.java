package com.kardoaward.mobileapp.request.controller;


import com.kardoaward.mobileapp.request.dto.request.StatusUserToRequest;
import com.kardoaward.mobileapp.request.dto.response.RequestDetailsDtoResponse;
import com.kardoaward.mobileapp.request.dto.response.RequestStageShortDtoResponse;
import com.kardoaward.mobileapp.request.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
            description = "visitor - зритель\nparticipant - участник"
    )
    @PatchMapping("/user/{userId}/event/{eventId}")
    public void addRequest(@PathVariable @Parameter(description = "ID участника.") Long userId,
                           @PathVariable @Parameter(description = "ID события.") Long eventId,
                           @RequestBody @Valid @Parameter(description = "сущность заявки.") StatusUserToRequest dto) {
        log.info("Пришел PATCH запрос requests/user/{}/event/{}: с телом: {}", userId, eventId, dto);
        requestService.addRequest(userId, eventId, dto);
        log.info("Отправлен статус 200 без тела");

    }

    @Operation(
            summary = "Активные события пользователя",
            description = "Возвращает все активные события."
    )
    @GetMapping("/active/{requesterId}")
    public List<RequestStageShortDtoResponse> findAllShortsActive(@PathVariable @Parameter(description = "ID заявки.") Long requesterId) {
        log.info("Пришел GET запрос requests/active/{}", requesterId);
        final List<RequestStageShortDtoResponse> response = requestService.findAllShortsActive(requesterId);
        log.info("Отправлен ответ для GET запроса requests/active/{} с телом: {}", requesterId, response);
        return response;
    }

    @Operation(
            summary = "Завершённые события пользователя",
            description = "Возвращает все завершённые события."
    )
    @GetMapping("/notActive/{requesterId}")
    public List<RequestStageShortDtoResponse> findAllShortsNotActive(@PathVariable @Parameter(description = "ID заявки.") Long requesterId) {
        log.info("Пришел GET запрос requests/notActive/{}", requesterId);
        final List<RequestStageShortDtoResponse> response = requestService.findAllShortsNotActive(requesterId);
        log.info("Отправлен ответ для GET запроса requests/notActive/{} с телом: {}", requesterId, response);
        return response;
    }

    @Operation(
            summary = "События пользователя с этапами.",
            description = "Подробное описание события пользователя с этапами."
    )
    @GetMapping("/details/{requesterId}")
    public RequestDetailsDtoResponse findByDetails(@PathVariable @Parameter(description = "ID заявки.") Long requesterId) {
        log.info("Пришел GET запрос requests/details/{}", requesterId);
        final RequestDetailsDtoResponse response = requestService.findByDetails(requesterId);
        log.info("Отправлен ответ для GET запроса requests/details/{} с телом: {}", requesterId, response);
        return response;
    }

}
