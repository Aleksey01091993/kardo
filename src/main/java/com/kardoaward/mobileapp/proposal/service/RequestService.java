package com.kardoaward.mobileapp.proposal.service;

import com.kardoaward.mobileapp.proposal.dto.response.RequestResponse;
import com.kardoaward.mobileapp.proposal.dto.request.StatusAdminToRequest;
import com.kardoaward.mobileapp.proposal.dto.request.StatusUserToRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RequestService {
    @Transactional
    void addRequest(Long userId, Long eventId, StatusUserToRequest dto);

    @Transactional
    void update(Long requestId, StatusAdminToRequest dto);

    List<RequestResponse> findAll();

    List<RequestResponse> findAllUserStatus(StatusUserToRequest dto);
}
