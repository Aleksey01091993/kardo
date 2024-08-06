package com.kardoaward.mobileapp.request.mapper;

import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.exeption.ConflictError;
import com.kardoaward.mobileapp.request.dto.request.UpdateRequestStage;
import com.kardoaward.mobileapp.request.dto.response.*;
import com.kardoaward.mobileapp.request.model.Request;
import com.kardoaward.mobileapp.request.model.RequestStage;
import com.kardoaward.mobileapp.request.dto.response.StageEventDtoResponse;
import com.kardoaward.mobileapp.stage.model.Stage;
import com.kardoaward.mobileapp.status.AdminEventStatus;
import com.kardoaward.mobileapp.status.UserStatus;
import com.kardoaward.mobileapp.user.model.User;

import java.time.LocalDate;
import java.util.List;


public class RequestMapper {

    public static Request mapAdd(final User user, final Event event, UserStatus status) {
        return Request.builder()
                .requester(user)
                .event(event)
                .statusToUser(status)
                .status(AdminEventStatus.NEW)
                .build();
    }

    public static List<RequestStage> mapAllStage(List<Stage> stages, Request request) {
        return stages.stream().map(o1 -> mapStage(o1, request)).toList();
    }

    public static Request mapUpdateRequest(RequestStage requestStage) {
        Request request = requestStage.getRequest();
        request.setStatusToUser(requestStage.getStatus());
        request.setCurrentStage(requestStage.getStage().getName());
        request.setCurrentPlace(requestStage.getResult());
        return request;
    }

    public static RequestStage mapStage(Stage stage, Request request) {
        return RequestStage.builder()
                .request(request)
                .stage(stage)
                .build();
    }

    public static RequestStage mapUpdateStage(RequestStage stage, UpdateRequestStage dto, UserStatus status) {
        if (dto.getResult() != null) {
            if (dto.getResult().equalsIgnoreCase("пройдено")) {
                stage.setResult(dto.getResult());
            } else {
                try {
                    stage.setResult(Integer.parseInt(dto.getResult()) + "");
                } catch (NumberFormatException e) {
                    throw new ConflictError("the result can be integer or \"пройдено\"");
                }
            }
        }
        if (dto.getStatus() != null) {
            stage.setStatus(status);
        }
        return stage;
    }

    public static RequestStageShortDtoResponse mapShortDto(Request request) {
        return new RequestStageShortDtoResponse(
                request.getEvent().getId(),
                request.getEvent().getName(),
                request.getEvent().getStart(),
                request.getEvent().getEnd(),
                request.getStatusToUser(),
                request.getCurrentStage(),
                request.getCurrentPlace(),
                100 / request.getRequestsStages().size() * request.getRequestsStages().stream()
                        .filter(o1 -> (o1.getStatus() == UserStatus.PARTICIPANT && o1.getResult().equalsIgnoreCase("пройдено"))
                                || o1.getStatus() == UserStatus.PASSED)
                        .toList().size() + "%"
        );
    }

    public static List<RequestStageShortDtoResponse> mapShotsAllDto(List<Request> requests) {
        return requests.stream().map(RequestMapper::mapShortDto).toList();
    }

    public static RequestDetailsDtoResponse mapDetailsDto(Request request) {
        return new RequestDetailsDtoResponse(
                request.getId(),
                request.getStatusToUser(),
                request.getEvent().getCategory(),
                request.getCurrentStage(),
                100 / request.getRequestsStages().size() * request.getRequestsStages().stream()
                        .filter(o1 -> (o1.getStatus() == UserStatus.PARTICIPANT && o1.getResult().equalsIgnoreCase("пройдено"))
                                || o1.getStatus() == UserStatus.PASSED)
                        .toList().size() + "%",
                request.getEvent().getDescription(),
                mapAllStageEventDto(request.getRequestsStages())
        );
    }

    public static List<StageEventDtoResponse> mapAllStageEventDto(List<RequestStage> requests) {
        return requests.stream().map(RequestMapper::mapStageEventDto).toList();
    }

    public static StageEventDtoResponse mapStageEventDto(RequestStage stage) {
        return new StageEventDtoResponse(
                stage.getId(),
                stage.getStage().getEnd().isBefore(LocalDate.now()) ? "Завершон" : "В процессе",
                stage.getStage().getName(),
                stage.getStage().getStart(),
                stage.getStage().getEnd(),
                stage.getStatus(),
                stage.getResult()
        );
    }
}
