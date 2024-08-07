package com.kardoaward.mobileapp.request.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Сущность для подтверждения отклонения заявки на участие\n\tconfirmed - подтверждена\n\trejected - отклонена\n\tnew - подана.")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StatusAdminToRequest {
    private String status;
}
