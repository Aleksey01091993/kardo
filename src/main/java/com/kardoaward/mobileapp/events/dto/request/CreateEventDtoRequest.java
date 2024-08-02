package com.kardoaward.mobileapp.events.dto.request;


import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NotNull
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDtoRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @FutureOrPresent
    private LocalDate startDate;
    @FutureOrPresent
    private LocalDate endDate;
}
