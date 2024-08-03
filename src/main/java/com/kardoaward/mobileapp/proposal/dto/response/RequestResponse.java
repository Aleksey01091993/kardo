package com.kardoaward.mobileapp.proposal.dto.response;

import com.kardoaward.mobileapp.status.AdminEventStatus;
import com.kardoaward.mobileapp.status.UserEventStatus;


public record RequestResponse(
        Long id,
        Long event,
        Long requester,
        UserEventStatus statusToUser,
        AdminEventStatus status
) {
    
}
