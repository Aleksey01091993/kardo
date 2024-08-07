package com.kardoaward.mobileapp.request.service.impl;

import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.events.repository.EventRepository;
import com.kardoaward.mobileapp.exeption.ConflictError;
import com.kardoaward.mobileapp.exeption.LocalDateRequestException;
import com.kardoaward.mobileapp.exeption.NotFoundException;
import com.kardoaward.mobileapp.exeption.NullRequestException;
import com.kardoaward.mobileapp.request.dto.request.StatusAdminToRequest;
import com.kardoaward.mobileapp.request.dto.request.StatusUserToRequest;
import com.kardoaward.mobileapp.request.dto.request.UpdateRequestStage;
import com.kardoaward.mobileapp.request.dto.response.RequestDetailsDtoResponse;
import com.kardoaward.mobileapp.request.dto.response.RequestStageShortDtoResponse;
import com.kardoaward.mobileapp.request.mapper.RequestMapper;
import com.kardoaward.mobileapp.request.model.Request;
import com.kardoaward.mobileapp.request.model.RequestStage;
import com.kardoaward.mobileapp.request.repository.RequestRepository;
import com.kardoaward.mobileapp.request.repository.RequestStageRepository;
import com.kardoaward.mobileapp.request.service.RequestService;
import com.kardoaward.mobileapp.status.AdminEventStatus;
import com.kardoaward.mobileapp.status.UserStatus;
import com.kardoaward.mobileapp.user.model.User;
import com.kardoaward.mobileapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;
    private final RequestStageRepository requestStageRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;


    @Override
    @Transactional
    public void addRequest(final Long userId, final Long eventId, final StatusUserToRequest dto) {
        Request isRequest = requestRepository.findByRequester_IdAndEvent_Id(userId, eventId).orElse(null);
        if (isRequest != null) {
            throw new ConflictError("the application for current events already exists");
        }
        Event event = findByEventId(eventId);
        if (event.getStart().isBefore(LocalDate.now())) {
            throw new ConflictError("You cannot apply for an event that has already passed or started");
        }
        requestRepository.save(RequestMapper.mapAdd(findByUserId(userId), event, isAddUserStatus(dto.getStatus())));
    }

    @Override
    @Transactional
    public void updateStatus(final Long requestId, final StatusAdminToRequest dto) {
        Request request = findByRequestId(requestId);
        if (request.getEvent().getStart().isBefore(LocalDate.now())) {
            requestRepository.delete(request);
            throw new ConflictError("you cannot update the status of an application for an event that has already" +
                    " passed or started, the application will be automatically deleted");
        }
        if (isAdminStatus(dto) == AdminEventStatus.CONFIRMED && request.getStatus() == AdminEventStatus.NEW) {
            request.setStatus(AdminEventStatus.CONFIRMED);
            requestStageRepository.saveAll(RequestMapper.mapAllStage(request.getEvent().getStages(), request));
            request.setCurrentStage(
                    request.getEvent().getStages().stream()
                            .sorted((o1, o2) -> o1.getStart().isBefore(o2.getStart()) ? 1 : -1)
                            .toList().get(0).getName()
            );
            requestRepository.save(request);
        } else if (isAdminStatus(dto) == AdminEventStatus.REJECTED && request.getStatus() == AdminEventStatus.CONFIRMED) {
            request.setStatus(AdminEventStatus.REJECTED);
            requestRepository.save(request);
            requestStageRepository.deleteAll(request.getRequestsStages());
        } else if (isAdminStatus(dto) == AdminEventStatus.REJECTED && request.getStatus() == AdminEventStatus.NEW) {
            request.setStatus(AdminEventStatus.REJECTED);
            requestRepository.save(request);
        }
    }

    @Override
    @Transactional
    public void updateRequestAndStage(final Long stageId, final Long requestId, final UpdateRequestStage dto) {
        RequestStage request = requestStageRepository.findByStage_IdAndRequest_Id(stageId, requestId)
                .orElseThrow(() -> new NotFoundException(String.format("Request stage with id %s not found", requestId)));
        if (request.getStage().getStart().isAfter(LocalDate.now()) || request.getStage().getEnd().isBefore(LocalDate.now())) {
            throw new LocalDateRequestException("you cannot update an application for a stage that has not yet started or that has already been completed");
        }
        RequestStage requestStage = requestStageRepository.save(RequestMapper.mapUpdateStage(request, dto, isUpdateUserStatus(dto.getStatus())));
        requestRepository.save(RequestMapper.mapUpdateRequest(requestStage));
    }

    @Override
    public List<RequestStageShortDtoResponse> findAllShortsActive(Long requesterId) {
        return RequestMapper.mapShotsAllDto(requestRepository
                .findAllByRequester_IdAndStatusToUserInAndEvent_EndAfter(
                        requesterId, List.of(UserStatus.PARTICIPANT, UserStatus.PASSED, UserStatus.VISITOR), LocalDate.now())
        );
    }

    @Override
    public List<RequestStageShortDtoResponse> findAllShortsNotActive(Long requesterId) {
        return RequestMapper.mapShotsAllDto(requestRepository
                .findAllByRequester_IdAndStatusToUserOrEvent_EndBefore(
                        requesterId, UserStatus.RETIRED, LocalDate.now())
        );
    }

    @Override
    public RequestDetailsDtoResponse findByDetails(Long requestId) {
        return RequestMapper.mapDetailsDto(findByRequestId(requestId));
    }

    private Request findByRequestId(final Long requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new NotFoundException("request not found by id: " + requestId));
    }

    private Event findByEventId(Long id) {
        return eventRepository.findById(id).orElseThrow(
                () -> new NullRequestException("Event with id " + id + " not found")
        );
    }

    private User findByUserId(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NullRequestException("User with id " + id + " not found")
        );
    }

    private UserStatus isAddUserStatus(final String dto) {
        return switch (dto) {
            case "visitor" -> UserStatus.VISITOR;
            case "participant" -> UserStatus.PARTICIPANT;
            default -> throw new ConflictError("Invalid request status: " + dto);
        };
    }

    private UserStatus isUpdateUserStatus(final String dto) {
        return switch (dto) {
            case "participant" -> UserStatus.PARTICIPANT;
            case "retired" -> UserStatus.RETIRED;
            case "passed" -> UserStatus.PASSED;
            default -> throw new ConflictError("Invalid request status: " + dto);
        };
    }

    private AdminEventStatus isAdminStatus(final StatusAdminToRequest dto) {
        return switch (dto.getStatus()) {
            case "confirmed" -> AdminEventStatus.CONFIRMED;
            case "rejected" -> AdminEventStatus.REJECTED;
            case "new" -> AdminEventStatus.NEW;
            default -> throw new ConflictError("Invalid request status: " + dto.getStatus());
        };
    }
}
