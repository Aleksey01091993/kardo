package com.kardoaward.mobileapp.request.controller;


import com.kardoaward.mobileapp.request.dto.request.StatusAdminToRequest;
import com.kardoaward.mobileapp.request.dto.request.UpdateRequestStage;
import com.kardoaward.mobileapp.request.service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("requests/admin/request")
public class AdminRequestController {

    private final RequestService requestService;

    @PatchMapping("/{requestId}")
    public void updateStatus(
            @PathVariable Long requestId,
            @RequestBody @Valid StatusAdminToRequest dto) {
        log.info("Пришел PATCH запрос requests/admin/request/{} с телом: {}", requestId, dto);
        requestService.updateStatus(requestId, dto);
        log.info("Отправлен статус 200 без тела");

    }

    @PatchMapping("/{requestId}/stage/{stageId}")
    public void updateRequestAndStage(
            @PathVariable Long stageId,
            @PathVariable Long requestId,
            @RequestBody @Valid UpdateRequestStage dto
    ) {
        log.info("Пришел PATCH запрос requests/{}/admin/request/{} с телом: {}", requestId, stageId, dto);
        requestService.updateRequestAndStage(stageId, requestId, dto);
        log.info("Отправлен статус 200 без тела");
    }
}
