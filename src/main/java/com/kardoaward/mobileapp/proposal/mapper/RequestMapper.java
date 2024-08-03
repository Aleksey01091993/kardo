package com.kardoaward.mobileapp.proposal.mapper;

import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.proposal.model.Requests;
import com.kardoaward.mobileapp.proposal.dto.request.StatusAdminToRequest;
import com.kardoaward.mobileapp.proposal.dto.request.StatusUserToRequest;
import com.kardoaward.mobileapp.proposal.dto.response.RequestResponse;
import com.kardoaward.mobileapp.status.AdminEventStatus;
import com.kardoaward.mobileapp.status.UserEventStatus;
import com.kardoaward.mobileapp.user.model.User;

import java.util.List;

public class RequestMapper {

    public static Requests mapAdd(final User user, final Event event, StatusUserToRequest dto) {
        return Requests.builder()
                .requester(user)
                .event(event)
                .statusToUser(isStatus(dto))
                .status(AdminEventStatus.NEW)
                .build();
    }

    public static Requests mapUpdate(Requests request, final StatusAdminToRequest dto) {
        request.setStatus(isAdmin(dto));
        return request;
    }

    public static UserEventStatus isStatus(final StatusUserToRequest dto) {
        if (dto.getStatus().equals("visitor")) {
            return UserEventStatus.VISITOR;
        } else if (dto.getStatus().equals("participant")) {
            return UserEventStatus.PARTICIPANT;
        } else {
            throw new ClassCastException("Invalid request status: " + dto.getStatus());
        }
    }

    public static AdminEventStatus isAdmin(final StatusAdminToRequest dto) {
        if (dto.getStatus().equals("confirmed")) {
            return AdminEventStatus.CONFIRMED;
        } else if (dto.getStatus().equals("rejected")) {
            return AdminEventStatus.REJECTED;
        } else if (dto.getStatus().equals("new")) {
            return AdminEventStatus.NEW;
        } else {
            throw new ClassCastException("Invalid request status: " + dto.getStatus());
        }
    }

    public static RequestResponse mapBy(Requests req) {
        return new RequestResponse(
                req.getId(),
                req.getEvent().getId(),
                req.getRequester().getId(),
                req.getStatusToUser(),
                req.getStatus()
        );
    }

    public static List<RequestResponse> mapAll(List<Requests> requests) {
        return requests.stream()
                .map(RequestMapper::mapBy)
                .toList();
    }
}
