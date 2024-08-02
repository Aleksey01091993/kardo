package com.kardoaward.mobileapp.request.service.impl;

import com.kardoaward.mobileapp.exeption.NotFoundException;
import com.kardoaward.mobileapp.exeption.NullRequestException;
import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.events.repository.EventRepository;
import com.kardoaward.mobileapp.request.dto.RequestResponse;
import com.kardoaward.mobileapp.request.dto.StatusAdminToRequest;
import com.kardoaward.mobileapp.request.dto.StatusUserToRequest;
import com.kardoaward.mobileapp.request.mapper.RequestMapper;
import com.kardoaward.mobileapp.request.model.RequestEvents;
import com.kardoaward.mobileapp.request.repository.RequestRepository;
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

    private final RequestRepository requestRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void addRequest(final Long userId, final Long eventId, final StatusUserToRequest dto) {
        requestRepository.save(RequestMapper.mapAdd(findByUserId(userId), findByEventId(eventId), dto));
    }

    @Override
    @Transactional
    public void update(final Long requestId, final StatusAdminToRequest dto) {
        requestRepository.save(RequestMapper.mapUpdate(findByRequestId(requestId), dto));
    }

    @Override
    public List<RequestResponse> findAll() {
        return RequestMapper.mapAll(requestRepository.findAll());
    }

    @Override
    public List<RequestResponse> findAllUserStatus(final StatusUserToRequest dto) {
        return RequestMapper.mapAll(requestRepository.findAllByStatusToUser(RequestMapper.isStatus(dto)));
    }

    @Override
    public List<RequestResponse> findAllAdminStatus(final StatusAdminToRequest dto) {
        return RequestMapper.mapAll(requestRepository.findAllByStatus(RequestMapper.isAdmin(dto)));
    }

    @Override
    public RequestResponse findById(final Long requestId) {
        return RequestMapper.mapBy(findByRequestId(requestId));
    }

    private RequestEvents findByRequestId(final Long requestId) {
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
}
