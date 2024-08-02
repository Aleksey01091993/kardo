package com.kardoaward.mobileapp.events.service;

import com.kardoaward.mobileapp.events.dto.event.request.CreateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.event.request.UpdateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.event.response.EventDtoResponse;
import com.kardoaward.mobileapp.events.dto.event.response.EventFullDtoResponse;
import com.kardoaward.mobileapp.events.dto.event.response.EventShortDtoResponse;
import com.kardoaward.mobileapp.events.model.Event;

import java.util.List;

public interface EventService {

    void create(CreateEventDtoRequest eventDto);

    void update(UpdateEventDtoRequest eventDto);

    void delete(Long id);

    List<EventShortDtoResponse> findAllShortsDto();

    EventShortDtoResponse findByEventShortId(Long id);

    EventDtoResponse findByEventDtoId(Long id);

    List<EventDtoResponse> findByAllEventDto();

    EventFullDtoResponse findByIdEventFullDto(Long id);

    List<EventFullDtoResponse> findAllEventFullDto();

    List<Event> updateStatus(List<Event> events);
}
