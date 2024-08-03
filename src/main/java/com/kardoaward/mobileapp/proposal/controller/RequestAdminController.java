package com.kardoaward.mobileapp.proposal.controller;


import com.kardoaward.mobileapp.proposal.dto.request.StatusAdminToRequest;
import com.kardoaward.mobileapp.proposal.dto.request.StatusUserToRequest;
import com.kardoaward.mobileapp.proposal.dto.response.RequestResponse;
import com.kardoaward.mobileapp.proposal.service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("requests/admin")
public class RequestAdminController {

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


    @GetMapping("/allUser")
    public List<RequestResponse> findAllUserStatus(StatusUserToRequest dto) {
        log.info("Пришел GET запрос requests/admin/request/allUser");
        final List<RequestResponse> response = requestService.findAllUserStatus(dto);
        log.info("Отправлен ответ для GET запроса requests/admin/request/allUser с телом: {}", response);
        return response;
    }
}
