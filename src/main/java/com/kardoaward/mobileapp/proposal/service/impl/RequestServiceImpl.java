package com.kardoaward.mobileapp.proposal.service.impl;

import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.events.repository.EventRepository;
import com.kardoaward.mobileapp.exeption.NullRequestException;
import com.kardoaward.mobileapp.proposal.dto.request.StatusAdminToRequest;
import com.kardoaward.mobileapp.proposal.dto.request.StatusUserToRequest;
import com.kardoaward.mobileapp.proposal.mapper.RequestMapper;
import com.kardoaward.mobileapp.proposal.model.Requests;
import com.kardoaward.mobileapp.proposal.repository.RequestRepository;
import com.kardoaward.mobileapp.proposal.dto.response.RequestResponse;
import com.kardoaward.mobileapp.proposal.service.RequestService;
import com.kardoaward.mobileapp.user.model.User;
import com.kardoaward.mobileapp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
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

    private Requests findByRequestId (Long requestId) {
        return requestRepository.findById(requestId).orElseThrow(
                () -> new NullRequestException("Requests with id " + requestId + " not found")
        );
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
