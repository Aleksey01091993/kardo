package com.kardoaward.mobileapp.request.controller;


import com.kardoaward.mobileapp.proposal.dto.request.StatusUserToRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("requests/user/{userId}/event/{eventId}")
public class UserRequestController {

    private final RequestService requestService;

    @PostMapping
    public void addRequest(@PathVariable Long userId,
                           @PathVariable Long eventId,
                           @RequestBody @Valid StatusUserToRequest dto) {
        log.info("Пришел POST запрос requests/user/{}/event/{}: {}", userId, eventId, dto);
        requestService.addRequest(userId, eventId, dto);
        log.info("Отправлен статус 201 без тела");

    }


}
