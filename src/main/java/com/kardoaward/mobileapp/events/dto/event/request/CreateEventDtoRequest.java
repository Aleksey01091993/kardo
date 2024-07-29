package com.kardoaward.mobileapp.events.dto.event.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEventDtoRequest {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
}
