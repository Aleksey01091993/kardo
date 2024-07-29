package com.kardoaward.mobileapp.events.dto.stage.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStageDtoRequest {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String task;
}
