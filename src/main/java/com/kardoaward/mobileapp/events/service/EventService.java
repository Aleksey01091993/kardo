package com.kardoaward.mobileapp.events.service;

import com.kardoaward.mobileapp.events.dto.request.CreateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.request.UpdateEventDtoRequest;
import com.kardoaward.mobileapp.events.dto.event.response.*;
import com.kardoaward.mobileapp.events.dto.response.*;
import com.kardoaward.mobileapp.events.model.Event;
import com.kardoaward.mobileapp.events.response.*;

import java.util.List;

public interface EventService {

    void create(CreateEventDtoRequest eventDto);

    void update(UpdateEventDtoRequest eventDto);

    List<EventNameDtoResponse> findAllName();

    List<EventToEpicDtoResponse> findAllToEpic();

    void delete(Long id);

    List<EventShortDtoResponse> findAllShortsDto();

    EventShortDtoResponse findByEventShortId(Long id);

    EventDtoResponse findByEventDtoId(Long id);

    List<EventDtoResponse> findByAllEventDto();

    EventFullDtoResponse findByIdEventFullDto(Long id);

    List<EventFullDtoResponse> findAllEventFullDto();

    List<Event> updateStatus(List<Event> events);
}
