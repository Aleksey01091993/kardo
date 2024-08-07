package com.kardoaward.mobileapp.events.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "Сущность для создания пользователя.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDtoRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String category;
    @FutureOrPresent
    private LocalDate startDate;
    @FutureOrPresent
    private LocalDate endDate;
}
