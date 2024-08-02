package com.kardoaward.mobileapp.request.dto;

import com.kardoaward.mobileapp.status.AdminRequest;
import com.kardoaward.mobileapp.status.UserRequest;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestResponse {
    private Long id;
    private Long event;
    private Long requester;
    private UserRequest statusToUser;
    private AdminRequest status;
}
