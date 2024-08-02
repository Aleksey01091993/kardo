package com.kardoaward.mobileapp.events.dto.stage.request;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
