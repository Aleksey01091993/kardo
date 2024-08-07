package com.kardoaward.mobileapp.request.controller;


import com.kardoaward.mobileapp.request.dto.request.StatusUserToRequest;
import com.kardoaward.mobileapp.request.dto.response.RequestDetailsDtoResponse;
import com.kardoaward.mobileapp.request.dto.response.RequestStageShortDtoResponse;
import com.kardoaward.mobileapp.request.service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/requests")
public class PrivateRequestController {


    private final RequestService requestService;

    @PatchMapping("/user/{userId}/event/{eventId}")
    public void addRequest(@PathVariable Long userId,
                           @PathVariable Long eventId,
                           @RequestBody @Valid StatusUserToRequest dto) {
        log.info("Пришел PATCH запрос requests/user/{}/event/{}: с телом: {}", userId, eventId, dto);
        requestService.addRequest(userId, eventId, dto);
        log.info("Отправлен статус 200 без тела");

    }

    @GetMapping("/active")
    public List<RequestStageShortDtoResponse> findAllShortsActive(Long requesterId) {
        log.info("Пришел GET запрос requests/active");
        final List<RequestStageShortDtoResponse> response = requestService.findAllShortsActive(requesterId);
        log.info("Отправлен ответ для GET запроса requests/active с телом: {}", response);
        return response;
    }

    @GetMapping("/notActive")
    public List<RequestStageShortDtoResponse> findAllShortsNotActive(Long requesterId) {
        log.info("Пришел GET запрос requests/notActive");
        final List<RequestStageShortDtoResponse> response = requestService.findAllShortsNotActive(requesterId);
        log.info("Отправлен ответ для GET запроса requests/notActive с телом: {}", response);
        return response;
    }

    @GetMapping("/details")
    public RequestDetailsDtoResponse findByDetails(Long requesterId) {
        log.info("Пришел GET запрос requests/details");
        final RequestDetailsDtoResponse response = requestService.findByDetails(requesterId);
        log.info("Отправлен ответ для GET запроса requests/details с телом: {}", response);
        return response;
    }

}
