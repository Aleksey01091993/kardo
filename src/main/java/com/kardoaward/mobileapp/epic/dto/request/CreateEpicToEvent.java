package com.kardoaward.mobileapp.epic.dto.request;


import jakarta.validation.constraints.NotBlank;
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
public class CreateEpicToEvent {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
}
