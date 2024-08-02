package com.kardoaward.mobileapp.request.controller;


import com.kardoaward.mobileapp.request.dto.RequestResponse;
import com.kardoaward.mobileapp.request.dto.StatusAdminToRequest;
import com.kardoaward.mobileapp.request.dto.StatusUserToRequest;
import com.kardoaward.mobileapp.request.service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("requests/admin/request")
public class AdminRequestController {

    private final RequestService requestService;

    @PostMapping("/{requestId}")
    public void updateRequest(
            @PathVariable Long requestId,
            @RequestBody @Valid StatusAdminToRequest dto) {
        log.info("Пришел POST запрос requests/admin/request/{} с телом: {}", requestId, dto);
        requestService.update(requestId, dto);
        log.info("Отправлен статус 201 без тела");

    }

    @GetMapping("/all")
    public List<RequestResponse> findAll() {
        log.info("Пришел GET запрос requests/admin/request/all");
        final List<RequestResponse> response = requestService.findAll();
        log.info("Отправлен ответ для GET запроса requests/admin/request/all с телом: {}", response);
        return response;
    }

    ;

    @GetMapping("/allUser")
    public List<RequestResponse> findAllUserStatus(StatusUserToRequest dto) {
        log.info("Пришел GET запрос requests/admin/request/allUser");
        final List<RequestResponse> response = requestService.findAllUserStatus(dto);
        log.info("Отправлен ответ для GET запроса requests/admin/request/allUser с телом: {}", response);
        return response;
    }

    ;

    @GetMapping("/allAdmin")
    public List<RequestResponse> findAllAdminStatus(StatusAdminToRequest dto) {
        log.info("Пришел GET запрос requests/admin/request/allAdmin");
        final List<RequestResponse> response = requestService.findAllAdminStatus(dto);
        log.info("Отправлен ответ для GET запроса requests/admin/request/allAdmin с телом: {}", response);
        return response;
    }

    ;

    @GetMapping("/{requestId}")
    public RequestResponse findById(@PathVariable Long requestId) {
        log.info("Пришел GET запрос requests/admin/request/{}", requestId);
        final RequestResponse response = requestService.findById(requestId);
        log.info("Отправлен ответ для GET запроса requests/admin/request/{} с телом: {}", requestId, response);
        return response;
    }

    ;
}
