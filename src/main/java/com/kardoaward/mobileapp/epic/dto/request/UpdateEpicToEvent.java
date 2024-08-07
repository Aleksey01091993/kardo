package com.kardoaward.mobileapp.epic.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Сущность для обновления подзадачи.")
@Data
@Builder
@NotNull
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEpicToEvent {
    private String name;
    private String description;
}
