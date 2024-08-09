package com.kardoaward.mobileapp.request.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Сущность для подачи заявки на участие.\n\tvisitor - зритель\n\tparticipant - участник.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusUserToRequest {
    private String status;
}
