package com.kardoaward.mobileapp.epic.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NotNull
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEpicToEvent {
    private String name;
    private String description;
}
