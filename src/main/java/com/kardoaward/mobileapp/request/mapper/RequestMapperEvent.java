package com.kardoaward.mobileapp.request.mapper;

import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.proposal.dto.response.RequestResponse;
import com.kardoaward.mobileapp.proposal.dto.request.StatusAdminToRequest;
import com.kardoaward.mobileapp.proposal.dto.request.StatusUserToRequest;
import com.kardoaward.mobileapp.request.dto.response.*;
import com.kardoaward.mobileapp.request.model.RequestEvents;
import com.kardoaward.mobileapp.request.model.RequestStage;
import com.kardoaward.mobileapp.stage.dto.response.StageEventDtoResponse;
import com.kardoaward.mobileapp.status.AdminEventStatus;
import com.kardoaward.mobileapp.status.StatusStage;
import com.kardoaward.mobileapp.status.UserEventStatus;
import com.kardoaward.mobileapp.user.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class RequestMapperEvent {

    public static List<RequestDetailsDtoResponse> mapAllDetails(List<RequestEvents> requestEvents) {
        return requestEvents.stream().map(RequestMapperEvent::mapDetails).toList();
    }

    public static RequestDetailsDtoResponse mapDetails(RequestEvents events) {
        return new RequestDetailsDtoResponse(
                events.getId(),
                events.getStatusToUser(),
                events.getEvent().getCategory(),
                Objects.requireNonNull(events.getEvent().getStages().stream()
                                .filter(o1 -> o1.getStart().isAfter(LocalDate.now()) && o1.getEnd().isBefore(LocalDate.now()))
                                .findAny()
                                .orElse(null))
                        .getName(),
                events.getLevel(),
                events.getEvent().getDescription(),
                mapAllEvent(events.getStages())
        );
    }

    public static List<StageEventDtoResponse> mapAllEvent(List<RequestStage> requestStages) {
        return requestStages.stream().map(RequestMapperEvent::mapEvent).toList();
    }

    public static StageEventDtoResponse mapEvent(RequestStage stage) {
        return new StageEventDtoResponse(
                stage.getId(),
                stage.getStage().getStart().isAfter(LocalDate.now()) ? StatusStage.COMPLETED : StatusStage.IN_PROGRESS,
                stage.getStage().getName(),
                stage.getStage().getStart(),
                stage.getStage().getEnd(),
                stage.getStatus(),
                stage.getResult(),
                stage.getStage().getTask(),
                mapParticipant(stage.getStage().getEvent().getRequesters())
        );

    }

    public static List<StageParticipantResponse> mapParticipant(List<RequestEvents> req) {
        return req.stream()
                .filter(o1 -> o1.getStatusToUser() == UserEventStatus.PARTICIPANT)
                .map(o1 -> new StageParticipantResponse(o1.getId(), o1.getRequester().getFirstName()))
                .toList();
    }

    public static List<RequestShortDtoResponse> mapAllPrivate(List<RequestEvents> requests) {
        return requests.stream().map(RequestMapperEvent::mapPrivate).toList();
    }

    public static RequestShortDtoResponse mapPrivate(final RequestEvents requestEvents) {
        return new RequestShortDtoResponse(
                requestEvents.getId(),
                requestEvents.getEvent().getName(),
                requestEvents.getEvent().getStart(),
                requestEvents.getEvent().getEnd(),
                requestEvents.getStatusToUser(),
                Objects.requireNonNull(requestEvents.getEvent().getStages().stream()
                                .filter(o1 -> o1.getStart().isAfter(LocalDate.now()) && o1.getEnd().isBefore(LocalDate.now()))
                                .findAny()
                                .orElse(null))
                        .getName(),
                requestEvents.getLevel(),
                requestEvents.getPlace()
        );
    }

    public static RequestDetailsDtoResponse mapPrivateDetails(final RequestEvents req) {
        return new RequestDetailsDtoResponse(
                req.getId(),
                req.getStatusToUser(),
                req.getEvent().getCategory(),
                Objects.requireNonNull(req.getEvent().getStages().stream()
                                .filter(o1 -> o1.getStart().isAfter(LocalDate.now()) && o1.getEnd().isBefore(LocalDate.now()))
                                .findAny()
                                .orElse(null))
                        .getName(),
                req.getLevel(),
                req.getEvent().getDescription(),

                )
    }

    public static RequestEvents mapAdd(final User user, final Event event, StatusUserToRequest dto) {
        return RequestEvents.builder()
                .requester(user)
                .event(event)
                .statusToUser(isStatus(dto))
                .status(AdminEventStatus.NEW)
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
                .map(RequestMapperEvent::mapBy)
                .toList();
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



}
