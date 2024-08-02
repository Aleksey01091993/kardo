package com.kardoaward.mobileapp.request.service;

import com.kardoaward.mobileapp.request.dto.RequestResponse;
import com.kardoaward.mobileapp.request.dto.StatusAdminToRequest;
import com.kardoaward.mobileapp.request.dto.StatusUserToRequest;

import java.util.List;

public interface RequestService {

    void addRequest(Long userId, Long eventId, StatusUserToRequest dto);

    void update(Long requestId, StatusAdminToRequest dto);

    List<RequestResponse> findAll();

    List<RequestResponse> findAllUserStatus(StatusUserToRequest dto);

    List<RequestResponse> findAllAdminStatus(StatusAdminToRequest dto);

    RequestResponse findById(Long requestId);
}
