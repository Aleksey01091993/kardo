package com.kardoaward.mobileapp.stage.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(description = "Сущность для создания этапа.")
@Data
@NotNull
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStageDtoRequest {
    @NotBlank
    private String name;
    @FutureOrPresent
    private LocalDate startDate;
    @FutureOrPresent
    private LocalDate endDate;
    @NotBlank
    private String task;
}
