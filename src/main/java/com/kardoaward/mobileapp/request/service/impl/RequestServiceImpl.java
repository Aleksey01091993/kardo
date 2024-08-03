package com.kardoaward.mobileapp.request.service.impl;

import com.kardoaward.mobileapp.exeption.NotFoundException;
import com.kardoaward.mobileapp.exeption.NullRequestException;
import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.events.repository.EventRepository;
import com.kardoaward.mobileapp.proposal.dto.response.RequestResponse;
import com.kardoaward.mobileapp.proposal.dto.request.StatusAdminToRequest;
import com.kardoaward.mobileapp.proposal.dto.request.StatusUserToRequest;
import com.kardoaward.mobileapp.request.mapper.RequestMapperEvent;
import com.kardoaward.mobileapp.request.model.RequestEvents;
import com.kardoaward.mobileapp.request.repository.RequestRepositoryEvent;
import com.kardoaward.mobileapp.request.service.RequestService;
import com.kardoaward.mobileapp.user.model.User;
import com.kardoaward.mobileapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RequestServiceImpl implements RequestService {

    private final RequestRepositoryEvent requestRepositoryEvent;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void addRequest(final Long userId, final Long eventId, final StatusUserToRequest dto) {
        requestRepositoryEvent.save(RequestMapperEvent.mapAdd(findByUserId(userId), findByEventId(eventId), dto));
    }

    @Override
    @Transactional
    public void update(final Long requestId, final StatusAdminToRequest dto) {
        requestRepositoryEvent.save(RequestMapperEvent.mapUpdate(findByRequestId(requestId), dto));
    }

    @Override
    public List<RequestResponse> findAll() {
        return RequestMapperEvent.mapAll(requestRepositoryEvent.findAll());
    }

    @Override
    public List<RequestResponse> findAllUserStatus(final StatusUserToRequest dto) {
        return RequestMapperEvent.mapAll(requestRepositoryEvent.findAllByStatusToUser(RequestMapperEvent.isStatus(dto)));
    }

    @Override
    public List<RequestResponse> findAllAdminStatus(final StatusAdminToRequest dto) {
        return RequestMapperEvent.mapAll(requestRepositoryEvent.findAllByStatus(RequestMapperEvent.isAdmin(dto)));
    }

    @Override
    public RequestResponse findById(final Long requestId) {
        return RequestMapperEvent.mapBy(findByRequestId(requestId));
    }



    private RequestEvents findByRequestId(final Long requestId) {
        return requestRepositoryEvent.findById(requestId)
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
}
