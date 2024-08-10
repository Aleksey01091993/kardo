package com.kardoaward.mobileapp.request.service.impl;

import com.kardoaward.mobileapp.config.UserDetailsImpl;
import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.events.repository.EventRepository;
import com.kardoaward.mobileapp.exceptions.ConflictError;
import com.kardoaward.mobileapp.exceptions.NotFoundException;
import com.kardoaward.mobileapp.exceptions.NullRequestException;
import com.kardoaward.mobileapp.request.dto.request.UpdateRequestStage;
import com.kardoaward.mobileapp.request.dto.response.RequestDetailsDtoResponse;
import com.kardoaward.mobileapp.request.dto.response.RequestStageShortDtoResponse;
import com.kardoaward.mobileapp.request.mapper.RequestMapper;
import com.kardoaward.mobileapp.request.model.Request;
import com.kardoaward.mobileapp.request.model.RequestStage;
import com.kardoaward.mobileapp.request.repository.RequestRepository;
import com.kardoaward.mobileapp.request.repository.RequestStageRepository;
import com.kardoaward.mobileapp.request.service.RequestService;
import com.kardoaward.mobileapp.status.UserStatus;
import com.kardoaward.mobileapp.user.model.User;
import com.kardoaward.mobileapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void addRequest(final Long eventId) {
        Request isRequest = requestRepository.findByRequester_IdAndEvent_Id(getUserId(), eventId).orElse(null);
        if (isRequest != null) {
            throw new ConflictError("the application for current events already exists");
        }
        Request request = requestRepository.save(RequestMapper.mapAdd(findByUserId(getUserId()), findByEventId(eventId)));
        requestStageRepository.saveAll(RequestMapper.mapAllStage(request.getEvent().getStages(), request));

    }

    @Override
    @Transactional
    public void updateRequestAndStage(final Long stageId, final Long requestId, final UpdateRequestStage dto) {
        RequestStage request = requestStageRepository.findByStage_IdAndRequest_Id(stageId, requestId)
                .orElseThrow(() -> new NotFoundException(String.format("Request stage with id %s not found", requestId)));
        RequestStage requestStage = requestStageRepository.save(RequestMapper.mapUpdateStage(request, dto, isUpdateUserStatus(dto.getStatus())));
        requestRepository.save(RequestMapper.mapUpdateRequest(requestStage));
    }

    @Override
    public List<RequestStageShortDtoResponse> findAllShortsActive(Long requesterId) {
        return RequestMapper.mapShotsAllDto(requestRepository
                .findAllByRequester_IdAndStatusToUserInAndEvent_EndAfter(
                        requesterId, List.of(UserStatus.PARTICIPANT, UserStatus.PASSED), LocalDate.now())
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

    private UserStatus isUpdateUserStatus(final String dto) {
        return switch (dto) {
            case "participant" -> UserStatus.PARTICIPANT;
            case "retired" -> UserStatus.RETIRED;
            case "passed" -> UserStatus.PASSED;
            default -> null;
        };
    }

    private Long getUserId() {
        return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser().getId();
    }
}
