package com.kardoaward.mobileapp.events.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "Сущность для обновления пользователя.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventDtoRequest {
    private String name;
    private String description;
    private String category;
    private String startDate;
    private String endDate;
}
