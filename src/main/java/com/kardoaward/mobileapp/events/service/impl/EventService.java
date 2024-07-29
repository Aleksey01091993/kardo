package com.kardoaward.mobileapp.events.service.impl;

import com.kardoaward.mobileapp.events.dto.event.request.CreateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.event.request.UpdateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.event.response.EventDtoResponse;
import com.kardoaward.mobileapp.events.dto.event.response.EventFullDtoResponse;
import com.kardoaward.mobileapp.events.dto.event.response.EventShortDtoResponse;
import com.kardoaward.mobileapp.events.model.Event;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EventService {

    @Transactional
    void create(CreateEventDtoRequest eventDto);

    @Transactional
    void update(UpdateEventDtoRequest eventDto);

    @Transactional
    void delete(Long id);

    List<EventShortDtoResponse> findAllShortsDto();

    EventShortDtoResponse findByEventShortId(Long id);

    EventDtoResponse findByEventDtoId(Long id);

    List<EventDtoResponse> findByAllEventDto();

    EventFullDtoResponse findByIdEventFullDto(Long id);

    List<EventFullDtoResponse> findAllEventFullDto();

    List<Event> updateStatus(List<Event> events);
}
