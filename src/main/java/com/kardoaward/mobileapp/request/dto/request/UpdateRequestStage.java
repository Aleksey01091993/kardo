package com.kardoaward.mobileapp.request.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Сущность для обновления статуса участника в этапе\ntatus:\n\tparticipant - участник\n\tretired - не прошол" +
        "\n\tpassed - прошёл.\nresult: Может быть целочисленным или должно быть \"пройдно\".")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequestStage {
    private String status;
    private String result;
}
