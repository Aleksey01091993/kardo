package com.kardoaward.mobileapp.request.mapper;

import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.request.dto.RequestResponse;
import com.kardoaward.mobileapp.request.dto.StatusAdminToRequest;
import com.kardoaward.mobileapp.request.dto.StatusUserToRequest;
import com.kardoaward.mobileapp.request.model.RequestEvents;
import com.kardoaward.mobileapp.status.AdminRequest;
import com.kardoaward.mobileapp.status.UserRequest;
import com.kardoaward.mobileapp.user.model.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RequestMapper {

    public static RequestEvents mapAdd(final User user, final Event event, StatusUserToRequest dto) {
        return RequestEvents.builder()
                .requester(user)
                .event(event)
                .statusToUser(isStatus(dto))
                .status(AdminRequest.NEW)
                .build();
    }

    public static RequestEvents mapUpdate(RequestEvents request, final StatusAdminToRequest dto) {
        request.setStatus(isAdmin(dto));
        return request;
    }

    public static RequestResponse mapBy(RequestEvents req) {
        return RequestResponse.builder()
                .id(req.getId())
                .event(req.getEvent().getId())
                .requester(req.getRequester().getId())
                .status(req.getStatus())
                .statusToUser(req.getStatusToUser())
                .build();
    }

    public static List<RequestResponse> mapAll(List<RequestEvents> requests) {
        return requests.stream()
                .map(RequestMapper::mapBy)
                .toList();
    }

    public static UserRequest isStatus(final StatusUserToRequest dto) {
        if (dto.getStatus().equals("visitor")) {
            return UserRequest.VISITOR;
        } else if (dto.getStatus().equals("participant")) {
            return UserRequest.PARTICIPANT;
        } else {
            throw new ClassCastException("Invalid request status: " + dto.getStatus());
        }
    }

    public static AdminRequest isAdmin(final StatusAdminToRequest dto) {
        if (dto.getStatus().equals("confirmed")) {
            return AdminRequest.CONFIRMED;
        } else if (dto.getStatus().equals("rejected")) {
            return AdminRequest.REJECTED;
        } else if (dto.getStatus().equals("new")) {
            return AdminRequest.NEW;
        } else {
            throw new ClassCastException("Invalid request status: " + dto.getStatus());
        }
    }



}
