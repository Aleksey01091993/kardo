package com.kardoaward.mobileapp.request.controller;


import com.kardoaward.mobileapp.request.dto.request.UpdateRequestStage;
import com.kardoaward.mobileapp.request.service.RequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Заявки на участия admin.", description = "Можно подтвердить, обновить статус заявки и статус участника в этапе.")
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/admin/requests")
public class AdminRequestController {

    private final RequestService requestService;


    @Operation(
            summary = "Обновление заявки.",
            description = "Позволяет обновить статус участника в этапе.\nparticipant - участник\nretired - не прошёл\npassed - прошёл"
    )
    @PatchMapping("/{requestId}/stage/{stageId}")
    public void updateRequestAndStage(
            @PathVariable @Parameter(description = "ID заявки.") Long requestId,
            @PathVariable @Parameter(description = "ID этапа.") Long stageId,
            @RequestBody @Valid @Parameter(description = "Сущность обновления участника этапа.") UpdateRequestStage dto
    ) {
        log.info("Пришел PATCH запрос admin/requests/{}/stage/{} с телом: {}", requestId, stageId, dto);
        requestService.updateRequestAndStage(stageId, requestId, dto);
        log.info("Отправлен статус 200 без тела");
    }
}
